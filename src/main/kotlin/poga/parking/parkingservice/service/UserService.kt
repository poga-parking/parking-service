package poga.parking.parkingservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import poga.parking.parkingservice.configuration.properties.PriceListProperties
import poga.parking.parkingservice.controller.model.input.UserInputDto
import poga.parking.parkingservice.controller.model.ouput.UserOutputDto
import poga.parking.parkingservice.enumeration.UserType
import poga.parking.parkingservice.exception.NotFoundErrorException
import poga.parking.parkingservice.repository.UserRepository
import poga.parking.parkingservice.support.toUser
import poga.parking.parkingservice.support.toUserOutputDto

@Service
class UserService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val userTypeService: UserTypeService,
    @Autowired private val priceListProperties: PriceListProperties,
) {
    fun registerUser(userInputDto: UserInputDto): UserOutputDto =
        userRepository.save(userInputDto.toUser()).toUserOutputDto()

    fun getPrice(uid: String): Double {
        return userRepository.findById(uid)
            .map { userTypeService.userType(it).getPrice() }
            .orElseThrow { NotFoundErrorException("No user with such uid($uid)") }
    }

    fun UserType.getPrice(): Double =
        priceListProperties.priceRates
            .filter { priceRate -> priceRate.type == this }
            .map { priceRate -> priceRate.price }
            .first()
}
