package poga.parking.parkingservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import poga.parking.parkingservice.enumeration.UserType

@Entity
@Table(name = "user", schema = "public")
data class User(
    @Id
    var id: String,

    @Column(name = "first_name")
    var firstName: String?,

    @Column(name = "second_name")
    var secondName: String?,

    @Column(name = "phone_number")
    var phoneNumber: String?,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    var type: UserType
)
