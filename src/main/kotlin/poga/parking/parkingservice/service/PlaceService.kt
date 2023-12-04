package poga.parking.parkingservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import poga.parking.parkingservice.controller.model.ouput.FreePlacesResponse
import poga.parking.parkingservice.repository.ParkingPlaceRepository

@Service
class PlaceService(
    @Autowired private val parkingPlaceRepository: ParkingPlaceRepository
) {

    fun getFreePlaces(): FreePlacesResponse = TODO()
}
