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
@Table(name = "user_transaction_statistics")
data class UserTransactionStatistics(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_transaction_generator"
    )
    @SequenceGenerator(
        name = "user_transaction_generator",
        sequenceName = "user_transaction_statistics_id_seq",
        allocationSize = 1
    )
    var id: Long? = null,

    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    var type: UserType,

    @Column(name = "price_rate", nullable = false)
    var priceRate: Double,

    @Column(name = "money_amount", nullable = false)
    var moneyAmount: Double,
)
