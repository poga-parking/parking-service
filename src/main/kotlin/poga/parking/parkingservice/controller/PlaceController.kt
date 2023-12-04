package poga.parking.parkingservice.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import poga.parking.parkingservice.controller.model.input.BookPlaceRequest
import poga.parking.parkingservice.controller.model.ouput.FreePlacesResponse
import poga.parking.parkingservice.controller.model.ouput.StatisticsResponse
import poga.parking.parkingservice.service.PlaceService
import poga.parking.parkingservice.support.toHttpStatusCode

@RestController
@RequestMapping("/place")
@Tag(name = "API for parking places")
class PlaceController(
    @Autowired private val placeService: PlaceService
) {

    @GetMapping("/free")
    @Operation(summary = "Get all free places")
    fun gerFreePlaces(): ResponseEntity<FreePlacesResponse> =
        ResponseEntity(
            placeService.getFreePlaces(),
            HttpStatus.OK.toHttpStatusCode()
        )

    @PostMapping("/book")
    @Operation(summary = "Book a parking place by user")
    fun bookPlace(request: BookPlaceRequest): ResponseEntity<Long> =
        TODO("$request")

    @PostMapping("/unbook/{id}")
    @Operation(summary = "Unbook a parking place by user")
    fun freeUpPlace(@PathVariable id: Long): ResponseEntity<Boolean> =
        TODO("$id")

    @GetMapping("/free/{id}")
    @Operation(summary = "Get statistics of a free parking place by id")
    fun getStatisticsAfterFreeUp(@PathVariable id: Long): ResponseEntity<StatisticsResponse> =
        TODO("$id")
}
