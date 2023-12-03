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
import poga.parking.parkingservice.enumeration.ParkingPlaceStatus

@Entity
@Table(name = "parking_place")
class ParkingPlace(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "parking_place_generator"
    )
    @SequenceGenerator(name = "parking_place_generator", sequenceName = "parking_place_id_seq", allocationSize = 1)
    var id: Long? = null,

    @Column(name = "place_number", nullable = false)
    var placeNumber: String,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: ParkingPlaceStatus
)
