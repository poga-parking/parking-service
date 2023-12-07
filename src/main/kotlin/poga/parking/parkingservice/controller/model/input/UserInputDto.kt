package poga.parking.parkingservice.controller.model.input

data class UserInputDto(
    val firstName: String,
    val secondName: String,
    val phoneNumber: String,
    val email: String,
    val password: String
)
