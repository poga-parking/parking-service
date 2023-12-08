package poga.parking.parkingservice.controller.model.ouput

data class UserOutputDto(
    val uid: String,
    val firstName: String?,
    val secondName: String?,
    val phoneNumber: String?,
    val email: String
)
