package poga.parking.parkingservice.service

import io.micrometer.core.annotation.Timed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import poga.parking.parkingservice.controller.model.input.BookPlaceDto
import poga.parking.parkingservice.controller.model.ouput.BookedPlaceOutputDto
import poga.parking.parkingservice.controller.model.ouput.FreePlacesOutputDto
import poga.parking.parkingservice.enumeration.ParkingPlaceStatus.FREE
import poga.parking.parkingservice.exception.InternalServerErrorException
import poga.parking.parkingservice.exception.NotFoundErrorException
import poga.parking.parkingservice.repository.ParkingPlaceRepository
import poga.parking.parkingservice.repository.UserRepository
import poga.parking.parkingservice.repository.UserStatisticsRepository
import poga.parking.parkingservice.service.money.MoneyService
import poga.parking.parkingservice.support.toFreePlacesOutputDto
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.jvm.optionals.getOrNull

@Service
class PlaceService(
    @Autowired private val parkingPlaceRepository: ParkingPlaceRepository,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val userStatisticsRepository: UserStatisticsRepository,
    @Autowired private val userTypeService: UserTypeService,
    @Autowired private val placeOccupierService: PlaceOccupierService,
    @Autowired private val moneyService: MoneyService
) {

    @Timed
    fun getFreePlaces(): FreePlacesOutputDto = parkingPlaceRepository
        .findAllByStatus(status = FREE)
        .toFreePlacesOutputDto(priceList = moneyService.getPriceList())

    @Timed
    fun bookPlace(bookPlaceDto: BookPlaceDto): BookedPlaceOutputDto {
        val user = bookPlaceDto
            .let { dto ->
                userRepository.findById(dto.uid).getOrNull()
                    ?: throw NotFoundErrorException("User with id(${dto.uid}) not found")
            }.also {
                it.type = userTypeService.userType(it)
                userRepository.save(it)
            }

        return BookedPlaceOutputDto(
            bookId = placeOccupierService.occupyPlace(user = user, bookPlaceDto = bookPlaceDto)
        )
    }

    /**
     * This method do the following in transaction:
     * 1. Start a money transaction for occupied hours for corresponding user type
     * 2. Update seat status in table to FREE
     * @return true if transaction ends successfully
     */
    @Timed
    @Transactional(propagation = Propagation.REQUIRED)
    fun unbookPlace(statsId: Long): Boolean {
        val departureDate = Instant.now()
        val userStatistics = userStatisticsRepository
            .findById(statsId)
            .getOrNull()
            ?: throw NotFoundErrorException("User statistics with id $statsId is not found")

        if (userStatistics.departureDate != null) {
            throw NotFoundErrorException("Active books with such id $statsId not found")
        }

        val user = userStatistics.user
            ?: throw InternalServerErrorException("User statistics ${userStatistics.id} doesn't have user")

        val numberOfOccupiedHours = ChronoUnit.HOURS.between(userStatistics.arrivalDate, departureDate)

        val userTransactionStatistics = moneyService.calculateAndPayMoney(
            user,
            numberOfOccupiedHours
        )

        userStatistics
            .apply {
                this.departureDate = departureDate
                this.parkingPlace?.status = FREE
                this.userTransactionStatistics = userTransactionStatistics
            }
            .also { userStatisticsRepository.save(it) }

        return true
    }
}
