package poga.parking.parkingservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import poga.parking.parkingservice.entity.ParkingPlace
import poga.parking.parkingservice.enumeration.ParkingPlaceStatus

@Repository
interface ParkingPlaceRepository : JpaRepository<ParkingPlace, Long> {

    fun findAllByStatus(status: ParkingPlaceStatus): List<ParkingPlace>

    fun findByPlaceNumber(placeNumber: String): ParkingPlace
}
