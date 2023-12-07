package poga.parking.parkingservice.support

import poga.parking.parkingservice.configuration.properties.PriceListProperties
import poga.parking.parkingservice.controller.model.input.UserInputDto
import poga.parking.parkingservice.controller.model.ouput.FreePlacesOutputDto
import poga.parking.parkingservice.controller.model.ouput.PriceList
import poga.parking.parkingservice.controller.model.ouput.PriceRate
import poga.parking.parkingservice.controller.model.ouput.StatisticsOutputDto
import poga.parking.parkingservice.controller.model.ouput.UserOutputDto
import poga.parking.parkingservice.entity.ParkingPlace
import poga.parking.parkingservice.entity.User
import poga.parking.parkingservice.entity.UserStatistics
import poga.parking.parkingservice.enumeration.UserType.GUEST
import poga.parking.parkingservice.exception.InternalServerErrorException

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

fun UserStatistics.toStatisticsOutputDto(): StatisticsOutputDto =
    StatisticsOutputDto(
        bookId = this.id
            ?: throw InternalServerErrorException("UserStatistics with id cannot be converted to StatisticsOutputDto"),
        placeNumber = this.parkingPlace?.placeNumber
            ?: throw InternalServerErrorException("Parking place cannot be null"),
        carBrand = this.carBrand,
        carPlate = this.carPlate,
        arrivalDate = this.arrivalDate,
        departureDate = this.departureDate,
        userType = this.user?.type
            ?: throw InternalServerErrorException("User in statistics cannot be null"),
        priceRate = this.userTransactionStatistics?.priceRate,
        moneyAmount = this.userTransactionStatistics?.moneyAmount
    )

fun UserInputDto.toUser(): User =
    User(
        id = null,
        firstName = this.firstName,
        secondName = this.secondName,
        phoneNumber = this.phoneNumber,
        email = this.email,
        password = this.password,
        type = GUEST,
    )

fun User.toUserOutputDto(): UserOutputDto =
    UserOutputDto(
        id = this.id ?: throw InternalServerErrorException("id in user cannot be null"),
        firstName = this.firstName,
        secondName = this.secondName,
        phoneNumber = this.phoneNumber,
        email = this.email ?: throw InternalServerErrorException("email cannot be null")
    )
