package poga.parking.parkingservice.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import poga.parking.parkingservice.enumeration.UserType
import kotlin.properties.Delegates

@ConfigurationProperties("price-list")
class PriceListProperties {
    lateinit var priceRates: List<PriceRate>
}

class PriceRate {
    lateinit var type: UserType
    var price by Delegates.notNull<Double>()
}
