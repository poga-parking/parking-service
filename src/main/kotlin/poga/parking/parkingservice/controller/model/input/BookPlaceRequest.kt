package poga.parking.parkingservice.controller.model.input

data class BookPlaceRequest(
    val firstName: String,
    val secondName: String,
    val carBrand: String,
    val carPlate: String,
    val email: String
)
