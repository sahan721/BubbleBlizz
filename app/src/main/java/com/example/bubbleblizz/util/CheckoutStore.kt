package com.example.bubbleblizz.util
import androidx.compose.runtime.mutableStateOf
enum class PaymentMethod { CARD, PAYPAL, GOOGLEPAY }
data class ShippingInfo(
    var fullName: String = "",
    var phone: String = "",
    var province: String = "",
    var city: String = "",
    var street: String = "",
    var postal: String = ""
)
data class CardInfo(
    var holder: String = "", var number: String = "", var expiry: String = "", var cvv: String = ""
)
object CheckoutStore {
    val shipping = mutableStateOf(ShippingInfo())
    val paymentMethod = mutableStateOf(PaymentMethod.CARD)
    val card = mutableStateOf(CardInfo())
    fun reset() { shipping.value = ShippingInfo(); paymentMethod.value = PaymentMethod.CARD; card.value = CardInfo() }
}
data class OrderSummary(
    val id: String,
    val items: Int,
    val total: Int,
    val shippingTo: String,
    val payment: PaymentMethod
)
object Orders {
    val last = mutableStateOf<OrderSummary?>(null)
}
