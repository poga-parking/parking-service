package poga.parking.parkingservice.support

import io.github.oshai.kotlinlogging.KLogger
import org.springframework.http.ResponseEntity

fun KLogger.logRequest(endpoint: String) =
    info { "{$endpoint}: Request received" }

fun KLogger.logResponse(endpoint: String, responseEntity: ResponseEntity<*>) =
    info { "{$endpoint}: Response with status code ${responseEntity.statusCode} sent" }

fun <T> (() -> ResponseEntity<T>).withLogging(
    endpoint: String,
    logger: KLogger
): ResponseEntity<T> = runCatching {
    logger.logRequest(endpoint)
    this()
}.onSuccess {
    logger.logResponse(endpoint = endpoint, responseEntity = it)
}.onFailure {
    logger.error { it }
}.getOrThrow()

