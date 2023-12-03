package poga.parking.parkingservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import poga.parking.parkingservice.entity.UserStatistics

interface UserStatisticsRepository : JpaRepository<UserStatistics, Long>
