package poga.parking.parkingservice.controller.model.ouput

data class FreePlacesResponse(
    val places: List<String>
) {
    val numberOfFreePlaces: Int = places.size
}
