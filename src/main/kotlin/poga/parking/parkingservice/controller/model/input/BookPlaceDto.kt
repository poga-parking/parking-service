package poga.parking.parkingservice.controller.model.input

data class BookPlaceDto(
    val carBrand: String,
    val carPlate: String,
    val email: String,
    val placeNumber: String
)
