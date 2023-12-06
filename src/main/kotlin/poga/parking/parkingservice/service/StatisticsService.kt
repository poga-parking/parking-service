package poga.parking.parkingservice.service

import io.micrometer.core.annotation.Timed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import poga.parking.parkingservice.controller.model.ouput.StatisticsOutputDto
import poga.parking.parkingservice.repository.UserStatisticsRepository
import poga.parking.parkingservice.support.toStatisticsOutputDto

@Service
class StatisticsService(
    @Autowired private val statisticsRepository: UserStatisticsRepository
) {

    @Timed
    fun totalStatistics(
        phoneNumber: String,
        bookedNow: Boolean
    ): List<StatisticsOutputDto> =
        statisticsRepository
            .findAll()
            .filter { it.user?.phoneNumber == phoneNumber }
            .filter { !bookedNow || (bookedNow && it.departureDate == null) }
            .map { it.toStatisticsOutputDto() }
}
