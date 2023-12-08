package poga.parking.parkingservice.service

import org.springframework.stereotype.Service
import poga.parking.parkingservice.entity.User
import poga.parking.parkingservice.enumeration.UserType

@Service
class UserTypeService {

    /**
     * Here we need to delegate logic to some University web-service
     * that gives us information about user if they are staff or student.
     * For now, it is done locally.
     * @param user is user to check its type
     * @return type of user in system
     */
    fun userType(user: User): UserType = when {
        user.email.endsWith("@innopolis.university") -> UserType.STUDENT
        user.email.endsWith("@innopolis.ru") -> UserType.STAFF
        else -> UserType.GUEST
    }
}
