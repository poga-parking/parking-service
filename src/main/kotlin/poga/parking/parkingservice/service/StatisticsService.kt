package poga.parking.parkingservice.service

import io.micrometer.core.annotation.Timed
import org.springframework.stereotype.Service
import poga.parking.parkingservice.controller.model.ouput.StatisticsOutputDto

@Service
class StatisticsService {

    @Timed
    fun totalStatistics(
        phoneNumber: String,
        bookedNow: Boolean
    ): List<StatisticsOutputDto> = TODO()
}
