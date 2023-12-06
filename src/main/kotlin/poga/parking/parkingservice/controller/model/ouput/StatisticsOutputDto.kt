package poga.parking.parkingservice.controller.model.ouput

import poga.parking.parkingservice.enumeration.UserType
import java.time.Instant

data class StatisticsOutputDto(
    val bookId: Long,
    val placeNumber: String,
    val carBrand: String,
    val carPlate: String,
    val arrivalDate: Instant,
    val userType: UserType,
    val departureDate: Instant?,
    val priceRate: Double?,
    val moneyAmount: Double?,
)
