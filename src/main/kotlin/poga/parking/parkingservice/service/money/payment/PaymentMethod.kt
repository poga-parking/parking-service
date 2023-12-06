package poga.parking.parkingservice.service.money.payment

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import poga.parking.parkingservice.entity.User

sealed interface PaymentMethod {
    fun pay(user: User, moneyAmount: Double) // деньги в double = плохо
}

@Service
@Qualifier("test")
class TestPaymentMethod : PaymentMethod {
    override fun pay(user: User, moneyAmount: Double) {
        logger.info { "Money transaction for $moneyAmount of user: $user sucessfully done" }
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}

