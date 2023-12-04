package poga.parking.parkingservice.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import poga.parking.parkingservice.controller.model.ouput.StatisticsOutputDto
import poga.parking.parkingservice.service.StatisticsService
import poga.parking.parkingservice.support.toHttpStatusCode
import poga.parking.parkingservice.support.withLogging

@RestController
@RequestMapping("/statistics")
@Tag(name = "API for retrieving statistics")
class StatisticsController(
    @Autowired private val statisticsService: StatisticsService
) {

    @GetMapping
    @Operation(summary = "Get statistics for a given phone number")
    fun totalStatistics(
        @RequestParam phoneNumber: String,
        @RequestParam bookedNow: Boolean
    ): ResponseEntity<List<StatisticsOutputDto>> = {
        ResponseEntity(
            statisticsService.totalStatistics(phoneNumber = phoneNumber, bookedNow = bookedNow),
            HttpStatus.OK.toHttpStatusCode()
        )
    }.withLogging(endpoint = GET_TOTAL_STATS_ENDPOINT, logger = logger)

    companion object {
        private val logger = KotlinLogging.logger { }

        private const val STATS_ENDPOINT = "/statistics"
        private const val GET_TOTAL_STATS_ENDPOINT = STATS_ENDPOINT
    }
}
