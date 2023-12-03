package poga.parking.parkingservice.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import poga.parking.parkingservice.entity.ParkingPlace
import poga.parking.parkingservice.entity.User
import poga.parking.parkingservice.entity.UserStatistics
import poga.parking.parkingservice.enumeration.ParkingPlaceStatus.FREE
import poga.parking.parkingservice.enumeration.UserType.STUDENT
import poga.parking.parkingservice.repository.ParkingPlaceRepository
import poga.parking.parkingservice.repository.UserRepository
import poga.parking.parkingservice.repository.UserStatisticsRepository
import java.time.Instant

@RestController
class ParkingController(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val parkingPlaceRepository: ParkingPlaceRepository,
    @Autowired private val userStatisticsRepository: UserStatisticsRepository
) {
    @GetMapping("/test")
    fun test(): String {
        val user = User(
            id = null,
            firstName = "Edik",
            secondName = "Zaripov",
            phoneNumber = "+79279388380",
            email = "e.zaripov@email.com",
            type = STUDENT
        )
        userRepository.save(user)

        val parkingPlace = ParkingPlace(
            placeNumber = "15C",
            status = FREE
        )
        parkingPlaceRepository.save(parkingPlace)

        val userStatistics = UserStatistics(
            user = user,
            parkingPlace = parkingPlace,
            carBrand = "BMW",
            carPlate = "Х000УЙ",
            arrivalDate = Instant.now(),
            departureDate = Instant.now(),
        )
        userStatisticsRepository.save(userStatistics)
        return "Hello!"
    }
}
