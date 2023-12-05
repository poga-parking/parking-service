package poga.parking.parkingservice.service

import io.micrometer.core.annotation.Timed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable
import poga.parking.parkingservice.controller.model.input.BookPlaceDto
import poga.parking.parkingservice.controller.model.ouput.FreePlacesOutputDto
import poga.parking.parkingservice.controller.model.ouput.StatisticsOutputDto
import poga.parking.parkingservice.entity.UserStatistics
import poga.parking.parkingservice.enumeration.ParkingPlaceStatus
import poga.parking.parkingservice.exception.NotFoundErrorException
import poga.parking.parkingservice.repository.ParkingPlaceRepository
import poga.parking.parkingservice.repository.UserRepository
import poga.parking.parkingservice.repository.UserStatisticsRepository
import poga.parking.parkingservice.support.toFreePlacesOutputDto
import poga.parking.parkingservice.support.toUser
import java.time.Instant
import kotlin.jvm.optionals.getOrNull

@Service
class PlaceService(
    @Autowired private val parkingPlaceRepository: ParkingPlaceRepository,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val userStatisticsRepository: UserStatisticsRepository
) {

    @Timed
    fun getFreePlaces(): FreePlacesOutputDto = parkingPlaceRepository
        .findAllByStatus(status = ParkingPlaceStatus.FREE)
        .toFreePlacesOutputDto()

    @Timed
    fun bookPlace(bookPlaceDto: BookPlaceDto): Long {

        /* TODO: извлечь тип юзера из стороннего сервиса для тарификации;
                 также надо бы отдать полученную в конфиге цену юзеру */
        val user = bookPlaceDto.toUser().let { inputUser ->
            userRepository.findByPhoneNumber(inputUser.phoneNumber)
                ?: userRepository.save(inputUser)
        }
        val userStatistics = bookPlaceDto.run {
            UserStatistics(
                user = user,
                parkingPlace = parkingPlaceRepository.findByPlaceNumber(placeNumber),
                carBrand = carBrand,
                carPlate = carPlate,
            )
        }
        return userStatisticsRepository.save(userStatistics).id!!
    }

    @Timed
    @Transactional(propagation = Propagation.REQUIRED)
    fun unbookPlace(statsId: Long): Boolean {
        val userStatistics = userStatisticsRepository
            .findById(statsId)
            .getOrNull()
            ?.apply { departureDate = Instant.now() }
            ?: throw NotFoundErrorException("$statsId")

        val user = userStatistics.user
        val parkingPlace = userStatistics.parkingPlace ?: throw Exception()

        TODO("""
            1. Сходить в конфиг за ценой по тарифу в зависимости от типа юзера
            2. Посчитать общую стоимость по времени пребывания
            3. Снять деньги в банке
            4. Отпустить место и апдейтнуть таблицу места
            5. Апдейтнеуть таблицу статы с новым departureDate
            7. В конце вернуть true, если ошибка => вернуть ошибку"""
        )
    }

    @Timed
    fun statisticsAfterFreeUp(@PathVariable id: Long): StatisticsOutputDto = TODO("$id")
}
