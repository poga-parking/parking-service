package poga.parking.parkingservice.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn.PATH
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import poga.parking.parkingservice.controller.model.input.UserInputDto
import poga.parking.parkingservice.controller.model.ouput.StatisticsOutputDto
import poga.parking.parkingservice.controller.model.ouput.UserOutputDto
import poga.parking.parkingservice.service.StatisticsService
import poga.parking.parkingservice.service.UserService
import poga.parking.parkingservice.support.toHttpStatusCode
import poga.parking.parkingservice.support.withLogging

@RestController
@RequestMapping("user")
@Tag(name = "API for user manipulation")
class UserController(
    @Autowired val userService: UserService,
    @Autowired val statisticsService: StatisticsService
) {
    @PostMapping("/signup")
    @Operation(summary = "Register user")
    fun registerUser(userInputDto: UserInputDto): ResponseEntity<UserOutputDto> = {
        ResponseEntity(
            userService.registerUser(userInputDto),
            HttpStatus.OK.toHttpStatusCode()
        )
    }.withLogging(endpoint = REGISTER_USER_ENDPOINT, logger = logger)

    @GetMapping("/price/{uid}")
    @Operation(summary = "Get price for uid")
    @Parameter(
        name = "uid",
        `in` = PATH,
        schema = Schema(type = "string"),
        description = "uid пользователя",
        example = "1234567890",
        required = true
    )
    fun getPrice(@PathVariable uid: String): ResponseEntity<Double> = {
        ResponseEntity(
            userService.getPrice(uid),
            HttpStatus.OK.toHttpStatusCode(),
        )
    }.withLogging(endpoint = GET_PRICE_ENDPOINT, logger = logger)

    @GetMapping("/statistics/{uid}")
    @Operation(summary = "Get statistics of user with uid")
    fun totalStatistics(
        @PathVariable uid: String,
        @RequestParam bookedNow: Boolean
    ): ResponseEntity<List<StatisticsOutputDto>> = {
        ResponseEntity(
            statisticsService.totalStatistics(uid = uid, bookedNow = bookedNow),
            HttpStatus.OK.toHttpStatusCode()
        )
    }.withLogging(endpoint = GET_TOTAL_STATS_ENDPOINT, logger = logger)

    companion object {
        private val logger = KotlinLogging.logger { }

        private const val USERS_ENDPOINT = "/user"
        private const val REGISTER_USER_ENDPOINT = "$USERS_ENDPOINT/signup"
        private const val GET_PRICE_ENDPOINT = "$USERS_ENDPOINT/price/{uid}"
        private const val GET_TOTAL_STATS_ENDPOINT = "$USERS_ENDPOINT/statistics/{uid}"
    }
}
