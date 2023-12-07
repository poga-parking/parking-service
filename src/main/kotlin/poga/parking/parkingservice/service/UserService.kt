package poga.parking.parkingservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import poga.parking.parkingservice.controller.model.input.UserAuthInputDto
import poga.parking.parkingservice.controller.model.input.UserInputDto
import poga.parking.parkingservice.controller.model.ouput.UserOutputDto
import poga.parking.parkingservice.exception.InvalidCreditsException
import poga.parking.parkingservice.repository.UserRepository
import poga.parking.parkingservice.support.toUser
import poga.parking.parkingservice.support.toUserOutputDto

@Service
class UserService(
    @Autowired private val userRepository: UserRepository
) {
    fun registerUser(userInputDto: UserInputDto): UserOutputDto =
        userRepository.save(userInputDto.toUser()).toUserOutputDto()

    fun authenticateUser(userAuthInputDto: UserAuthInputDto): UserOutputDto {
        return userRepository
            .findByEmailAndPassword(userAuthInputDto.email, userAuthInputDto.password)?.toUserOutputDto()
            ?: throw InvalidCreditsException("No user with such email and password")
    }
}
