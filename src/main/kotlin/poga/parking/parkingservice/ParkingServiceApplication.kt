package poga.parking.parkingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ParkingServiceApplication

fun main(args: Array<String>) {
	runApplication<ParkingServiceApplication>(*args)
}
