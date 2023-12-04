package poga.parking.parkingservice.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import poga.parking.parkingservice.enumeration.UserType
import java.math.BigDecimal

@ConfigurationProperties
class PriceListProperties {

    var
}

class PriceRate {
    lateinit var type: UserType
    lateinit var price: BigDecimal
}
