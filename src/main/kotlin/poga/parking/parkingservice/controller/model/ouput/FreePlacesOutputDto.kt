package poga.parking.parkingservice.controller.model.ouput

data class FreePlacesOutputDto(
    val places: List<String>
) {
    val numberOfFreePlaces: Int = places.size
}
