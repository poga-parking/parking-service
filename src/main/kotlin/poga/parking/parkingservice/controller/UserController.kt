package poga.parking.parkingservice.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import poga.parking.parkingservice.controller.model.input.UserAuthInputDto
import poga.parking.parkingservice.controller.model.input.UserInputDto
import poga.parking.parkingservice.controller.model.ouput.UserOutputDto
import poga.parking.parkingservice.service.UserService
import poga.parking.parkingservice.support.toHttpStatusCode
import poga.parking.parkingservice.support.withLogging

@RestController
@RequestMapping("user")
@Tag(name = "API for user registration and auth")
class UserController(
    @Autowired val userService: UserService
) {
    @PostMapping("/signup")
    @Operation(summary = "Register user")
    fun registerUser(userInputDto: UserInputDto): ResponseEntity<UserOutputDto> = {
        ResponseEntity(
            userService.registerUser(userInputDto),
            HttpStatus.OK.toHttpStatusCode()
        )
    }.withLogging(endpoint = REGISTER_USER_ENDPOINT, logger = logger)

    @PostMapping("/signin")
    @Operation(summary = "Authenticate user")
    fun authenticateUser(userAuthInputDto: UserAuthInputDto): ResponseEntity<UserOutputDto> = {
        ResponseEntity(
            userService.authenticateUser(userAuthInputDto),
            HttpStatus.OK.toHttpStatusCode()
        )
    }.withLogging(endpoint = AUTH_USER_ENDPOINT, logger = logger)

    companion object {
        private val logger = KotlinLogging.logger { }

        private const val USERS_ENDPOINT = "/user"
        private const val REGISTER_USER_ENDPOINT = "$USERS_ENDPOINT/signup"
        private const val AUTH_USER_ENDPOINT = "$USERS_ENDPOINT/signin"
    }
}
