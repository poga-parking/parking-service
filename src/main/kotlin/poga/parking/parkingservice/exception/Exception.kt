package poga.parking.parkingservice.exception

open class ParkingServiceException(message: String) :
    Exception(message)

open class ParkingPlaceAlreadyOccupiedException(placeNumber: String) :
    Exception("The place $placeNumber is already occupied")

open class InternalServerErrorException(message: String) :
    ParkingServiceException(message = message)

open class NotFoundErrorException(message: String) :
    ParkingServiceException(message = message)

open class InvalidCreditsException(message: String) :
    Exception(message)

