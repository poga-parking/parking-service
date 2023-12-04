package poga.parking.parkingservice.controller.advice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import poga.parking.parkingservice.controller.PlaceController
import poga.parking.parkingservice.controller.StatisticsController
import poga.parking.parkingservice.controller.model.ouput.ErrorResponse
import java.lang.Exception

@RestControllerAdvice(basePackageClasses = [PlaceController::class, StatisticsController::class])
class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleSomeException(exception: Exception): ErrorResponse =
        ErrorResponse(
            message = exception.message
                ?: "Some internal server error"
        )
}
