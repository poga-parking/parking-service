package poga.parking.parkingservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import poga.parking.parkingservice.entity.ParkingPlace
import poga.parking.parkingservice.enumeration.ParkingPlaceStatus

@Repository
interface ParkingPlaceRepository : JpaRepository<ParkingPlace, Long> {

    fun findAllByStatus(status: ParkingPlaceStatus): List<ParkingPlace>

    fun findByPlaceNumber(placeNumber: String): ParkingPlace?

    @Transactional
    @Modifying
    @Query("update ParkingPlace p set p.status = ?1 where p.id = ?2")
    fun updateStatusById(status: ParkingPlaceStatus, id: Long)
}
