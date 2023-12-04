package poga.parking.parkingservice.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import poga.parking.parkingservice.controller.model.ouput.StatisticsResponse
import poga.parking.parkingservice.service.StatisticsService

@RestController("/statistics")
class StatisticsController(
    @Autowired private val statisticsService: StatisticsService
) {

    @GetMapping
    fun getTotalStatistics(
        @RequestParam phoneNumber: String,
        @RequestParam isBookedNow: Boolean
    ): ResponseEntity<List<StatisticsResponse>> = TODO("$phoneNumber $isBookedNow")
}
