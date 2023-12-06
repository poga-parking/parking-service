package poga.parking.parkingservice.controller.model.ouput

import java.time.Instant

data class StatisticsOutputDto(
    val bookId: Long,
    val placeNumber: String,
    val carBrand: String,
    val carPlate: String,
    val arrivalDate: Instant,
    val departureDate: Instant?
)
