package poga.parking.parkingservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import poga.parking.parkingservice.controller.model.input.BookPlaceDto
import poga.parking.parkingservice.entity.User
import poga.parking.parkingservice.entity.UserStatistics
import poga.parking.parkingservice.enumeration.ParkingPlaceStatus.BOOKED
import poga.parking.parkingservice.exception.InternalServerErrorException
import poga.parking.parkingservice.exception.NotFoundErrorException
import poga.parking.parkingservice.exception.ParkingPlaceAlreadyOccupiedException
import poga.parking.parkingservice.repository.ParkingPlaceRepository
import poga.parking.parkingservice.repository.UserStatisticsRepository

@Service
class PlaceOccupierService(
    @Autowired private val parkingPlaceRepository: ParkingPlaceRepository,
    @Autowired private val userStatisticsRepository: UserStatisticsRepository,
) {

    @Transactional(propagation = Propagation.REQUIRED)
    fun occupyPlace(user: User, bookPlaceDto: BookPlaceDto): Long {
        val parkingPlace = parkingPlaceRepository.findByPlaceNumber(bookPlaceDto.placeNumber)
            ?: throw NotFoundErrorException("Parking place with number ${bookPlaceDto.placeNumber} is not found")

        if (parkingPlace.status == BOOKED) {
            throw ParkingPlaceAlreadyOccupiedException(parkingPlace.placeNumber)
        }

        val userStatistics = UserStatistics(
            user = user,
            parkingPlace = parkingPlace,
            carBrand = bookPlaceDto.carBrand,
            carPlate = bookPlaceDto.carPlate,
        )
        parkingPlaceRepository.updateStatusById(
            status = BOOKED,
            id = parkingPlace.id ?: throw InternalServerErrorException("Parking place id must not be null")
        )

        return userStatisticsRepository.save(userStatistics).id
            ?: throw InternalServerErrorException("User statistics id must not be null")
    }
}
