package poga.parking.parkingservice.support

import poga.parking.parkingservice.configuration.properties.PriceListProperties
import poga.parking.parkingservice.controller.model.input.BookPlaceDto
import poga.parking.parkingservice.controller.model.ouput.FreePlacesOutputDto
import poga.parking.parkingservice.controller.model.ouput.PriceList
import poga.parking.parkingservice.controller.model.ouput.PriceRate
import poga.parking.parkingservice.entity.ParkingPlace
import poga.parking.parkingservice.entity.User
import poga.parking.parkingservice.enumeration.UserType

fun BookPlaceDto.toUser() = User(
    firstName = firstName,
    secondName = secondName,
    phoneNumber = phoneNumber,
    email = email,
    type = UserType.GUEST,
)

fun List<ParkingPlace>.toFreePlacesOutputDto(priceList: PriceList) = FreePlacesOutputDto(
    places = map { it.placeNumber },
    priceList = priceList
)

fun PriceListProperties.toPriceList() = PriceList(
    priceRates = priceRates.map {
        PriceRate(
            userType = it.type.toString(),
            price = it.price
        )
    }
)

