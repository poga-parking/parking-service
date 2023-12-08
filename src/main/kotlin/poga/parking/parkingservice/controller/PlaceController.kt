package poga.parking.parkingservice.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import poga.parking.parkingservice.controller.model.input.BookPlaceDto
import poga.parking.parkingservice.controller.model.ouput.BookedPlaceOutputDto
import poga.parking.parkingservice.controller.model.ouput.FreePlacesOutputDto
import poga.parking.parkingservice.service.PlaceService
import poga.parking.parkingservice.support.toHttpStatusCode
import poga.parking.parkingservice.support.withLogging

@RestController
@RequestMapping("/place")
@Tag(name = "API for parking places")
class PlaceController(
    @Autowired private val placeService: PlaceService
) {

    @GetMapping("/free")
    @Operation(summary = "Get all free places")
    fun freePlaces(): ResponseEntity<FreePlacesOutputDto> = {
        ResponseEntity(
            placeService.getFreePlaces(),
            HttpStatus.OK.toHttpStatusCode()
        )
    }.withLogging(endpoint = GET_FREE_PLACES_ENDPOINT, logger = logger)

    @PostMapping("/book", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "Book a parking place by user")
    fun bookPlace(@RequestBody bookPlaceDto: BookPlaceDto): ResponseEntity<BookedPlaceOutputDto> = {
        ResponseEntity(
            placeService.bookPlace(bookPlaceDto = bookPlaceDto),
            HttpStatus.OK.toHttpStatusCode()
        )
    }.withLogging(endpoint = BOOK_PLACE_ENDPOINT, logger = logger)

    @PostMapping("/unbook/{id}")
    @Operation(summary = "Unbook a parking place by user")
    fun unbookPlace(@PathVariable id: Long): ResponseEntity<Boolean> = {
        ResponseEntity(
            placeService.unbookPlace(statsId = id),
            HttpStatus.OK.toHttpStatusCode()
        )
    }.withLogging(endpoint = UNBOOK_PLACE_ENDPOINT, logger = logger)

    companion object {
        private val logger = KotlinLogging.logger { }

        private const val PLACES_ENDPOINT = "/place"
        private const val GET_FREE_PLACES_ENDPOINT = "$PLACES_ENDPOINT/free"
        private const val BOOK_PLACE_ENDPOINT = "$PLACES_ENDPOINT/book"
        private const val UNBOOK_PLACE_ENDPOINT = "$PLACES_ENDPOINT/unbook/{id}"
    }
}

