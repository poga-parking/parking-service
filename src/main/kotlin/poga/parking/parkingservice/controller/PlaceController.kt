package poga.parking.parkingservice.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import poga.parking.parkingservice.controller.model.input.BookPlaceRequest
import poga.parking.parkingservice.controller.model.ouput.FreePlacesResponse
import poga.parking.parkingservice.controller.model.ouput.StatisticsReponse
import poga.parking.parkingservice.service.PlaceService
import poga.parking.parkingservice.support.toHttpStatusCode

@RestController("/place")
class PlaceController(
    @Autowired private val placeService: PlaceService
) {

    @GetMapping("/free")
    fun gerFreePlaces(): ResponseEntity<FreePlacesResponse> =
        ResponseEntity(
            placeService.getFreePlaces(),
            HttpStatus.OK.toHttpStatusCode()
        )

    @PostMapping("/book")
    fun bookPlace(request: BookPlaceRequest): ResponseEntity<Long> =
        TODO("$request")

    @PostMapping("/free/{id}")
    fun freeUpPlace(@PathVariable id: Long): ResponseEntity<Boolean> =
        TODO("$id")

    @GetMapping("/free/{id}")
    fun getStatisticsAfterFreeUp(@PathVariable id: Long): ResponseEntity<StatisticsReponse> =
        TODO("$id")
}
