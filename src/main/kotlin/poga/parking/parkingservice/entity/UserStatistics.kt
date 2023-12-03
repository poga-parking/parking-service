package poga.parking.parkingservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "user_statistics")
class UserStatistics(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_statistics_generator"
    )
    @SequenceGenerator(name = "user_statistics_generator", sequenceName = "user_statistics_id_seq", allocationSize = 1)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", referencedColumnName = "id")
    var parkingPlace: ParkingPlace? = null,

    @Column(name = "car_brand", nullable = false)
    var carBrand: String,

    @Column(name = "car_plate", nullable = false)
    var carPlate: String,

    @Column(name = "arrival_date", nullable = false)
    var arrivalDate: Instant,

    @Column(name = "departure_date", nullable = true)
    var departureDate: Instant,
)

