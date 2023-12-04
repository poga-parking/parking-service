package poga.parking.parkingservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import poga.parking.parkingservice.controller.model.input.BookPlaceDto
import poga.parking.parkingservice.controller.model.ouput.FreePlacesOutputDto
import poga.parking.parkingservice.entity.User
import poga.parking.parkingservice.enumeration.UserType.GUEST
import poga.parking.parkingservice.repository.ParkingPlaceRepository

@Service
class PlaceService(
    @Autowired private val parkingPlaceRepository: ParkingPlaceRepository
) {

    fun getFreePlaces(): FreePlacesOutputDto = TODO()

    fun bookPlace(bookPlaceDto: BookPlaceDto) {
        val user = User(
            id = null,
            firstName = bookPlaceDto.firstName,
            secondName = bookPlaceDto.secondName,
            phoneNumber = bookPlaceDto.phoneNumber,
            email = bookPlaceDto.email,
            type = GUEST,
        )
    }
}
