package poga.parking.parkingservice.controller.model.input

data class UserInputDto(
    val email: String,
    val uid: String,
    val firstName: String?,
    val secondName: String?,
    val phoneNumber: String?,
)
