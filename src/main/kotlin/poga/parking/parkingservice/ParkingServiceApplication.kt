package poga.parking.parkingservice

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import poga.parking.parkingservice.configuration.properties.PriceListProperties

@SpringBootApplication
@OpenAPIDefinition(servers = [Server(url = "https://api.pogaparking.ru", description = "Default Server URL")])
@ConfigurationPropertiesScan(basePackageClasses = [PriceListProperties::class])
class ParkingServiceApplication

fun main(args: Array<String>) {
    runApplication<ParkingServiceApplication>(*args)
}
