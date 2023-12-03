package poga.parking.parkingservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import poga.parking.parkingservice.entity.User

interface UserRepository : JpaRepository<User, Long>
