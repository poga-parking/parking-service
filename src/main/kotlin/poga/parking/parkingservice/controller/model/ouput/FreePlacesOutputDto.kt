package poga.parking.parkingservice.controller.model.ouput

data class FreePlacesOutputDto(
    val places: List<String>,
    val priceList: PriceList
) {
    val numberOfFreePlaces: Int = places.size
}

data class PriceList(val priceRates: List<PriceRate>)

data class PriceRate(val userType: String, val price: Double)
