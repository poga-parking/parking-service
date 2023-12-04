package poga.parking.parkingservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import poga.parking.parkingservice.entity.UserStatistics

@Repository
interface UserStatisticsRepository : JpaRepository<UserStatistics, Long>
