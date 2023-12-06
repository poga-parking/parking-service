package poga.parking.parkingservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import poga.parking.parkingservice.entity.UserTransactionStatistics

interface UserTransactionStatisticsRepository : JpaRepository<UserTransactionStatistics, Long>
