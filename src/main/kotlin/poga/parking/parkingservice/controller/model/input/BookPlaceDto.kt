package poga.parking.parkingservice.controller.model.input

data class BookPlaceDto(
    val carBrand: String,
    val carPlate: String,
    val uid: String,
    val placeNumber: String
)
