package poga.parking.parkingservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import poga.parking.parkingservice.enumeration.UserType

@Entity
@Table(name = "user", schema = "public")
data class User(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_generator"
    )
    @SequenceGenerator(name = "user_generator", sequenceName = "user_id_seq", allocationSize = 1)
    var id: Long? = null,

    @Column(name = "first_name", nullable = false)
    var firstName: String,

    @Column(name = "second_name", nullable = false)
    var secondName: String,

    @Column(name = "phone_number", unique = true, nullable = false)
    var phoneNumber: String,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    var type: UserType
)
