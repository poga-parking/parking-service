package poga.parking.parkingservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import poga.parking.parkingservice.entity.ParkingPlace

interface ParkingPlaceRepository : JpaRepository<ParkingPlace, Long>
