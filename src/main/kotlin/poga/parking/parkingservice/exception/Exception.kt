package poga.parking.parkingservice.exception

open class ParkingServiceException(message: String) :
    Exception(message = message)

open class InternalServerErrorException(message: String) :
    ParkingServiceException(message = message)

open class NotFoundErrorException(message: String) :
    ParkingServiceException(message = message)

