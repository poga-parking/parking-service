package poga.parking.parkingservice.service

import org.springframework.stereotype.Service
import poga.parking.parkingservice.controller.model.ouput.StatisticsOutputDto

@Service
class StatisticsService {

    fun totalStatistics(
        phoneNumber: String,
        bookedNow: Boolean
    ): List<StatisticsOutputDto> = TODO()
}
