package poga.parking.parkingservice.service.money

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import poga.parking.parkingservice.configuration.properties.PriceListProperties
import poga.parking.parkingservice.controller.model.ouput.PriceList
import poga.parking.parkingservice.entity.User
import poga.parking.parkingservice.entity.UserTransactionStatistics
import poga.parking.parkingservice.enumeration.UserType
import poga.parking.parkingservice.repository.UserTransactionStatisticsRepository
import poga.parking.parkingservice.service.money.payment.PaymentMethod
import poga.parking.parkingservice.support.toPriceList

@Service
class MoneyService(
    @Autowired private val priceListProperties: PriceListProperties,
    @Autowired @Qualifier("test") private val paymentMethod: PaymentMethod,
    @Autowired private val userTransactionStatisticsRepository: UserTransactionStatisticsRepository
) {
    @Transactional
    fun calculateAndPayMoney(user: User, hours: Long): UserTransactionStatistics {
        user
            .type.getPrice()
            .also {
                val moneyAmount = it * hours
                paymentMethod.pay(user, it * hours)

                return userTransactionStatisticsRepository.save(
                    UserTransactionStatistics(
                        id = null,
                        type = user.type,
                        priceRate = it,
                        moneyAmount = moneyAmount,
                    )
                )
            }
    }

    fun getPriceList(): PriceList = priceListProperties.toPriceList()

    fun UserType.getPrice(): Double =
        priceListProperties.priceRates
            .filter { priceRate -> priceRate.type == this }
            .map { priceRate -> priceRate.price }
            .first()
}
