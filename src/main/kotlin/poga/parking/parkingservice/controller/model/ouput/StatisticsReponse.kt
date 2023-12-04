package poga.parking.parkingservice.controller.model.ouput

import java.time.Instant

data class StatisticsReponse(
    val bookId: Long,
    val placeNumber: Long,
    val carBrand: String,
    val carPlate: String,
    val arrivalDate: Instant,
    val departureDate: Instant
)
