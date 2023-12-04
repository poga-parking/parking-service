package poga.parking.parkingservice.support

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

fun HttpStatus.toHttpStatusCode(): HttpStatusCode =
    HttpStatusCode.valueOf(value())
