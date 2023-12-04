package poga.parking.parkingservice.controller.model.input

data class BookPlaceDto(
    val firstName: String,
    val secondName: String,
    val carBrand: String,
    val carPlate: String,
    val phoneNumber: String,
    val email: String,
    val placeNumber: String
)
