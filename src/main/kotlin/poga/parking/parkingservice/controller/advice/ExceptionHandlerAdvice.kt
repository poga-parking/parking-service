package poga.parking.parkingservice.controller.advice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import poga.parking.parkingservice.controller.PlaceController
import poga.parking.parkingservice.controller.StatisticsController
import poga.parking.parkingservice.controller.model.ouput.ErrorResponse
import poga.parking.parkingservice.exception.InternalServerErrorException
import poga.parking.parkingservice.exception.NotFoundErrorException

@RestControllerAdvice(basePackageClasses = [PlaceController::class, StatisticsController::class])
class ExceptionHandlerAdvice {

    @ExceptionHandler(InternalServerErrorException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerErrorException(exception: InternalServerErrorException): ErrorResponse =
        ErrorResponse(
            message = exception.message ?: "Some internal server error"
        )

    @ExceptionHandler(NotFoundErrorException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundErorException(exception: NotFoundErrorException): ErrorResponse =
        ErrorResponse(
            message = exception.message ?: "Something is not found"
        )
}
