package com.example.bubbleblizz.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import com.example.bubbleblizz.ui.component.BackTopBar
import com.example.bubbleblizz.ui.component.GradientButton
import com.example.bubbleblizz.util.*

@Composable
fun CheckoutShippingScreen(onBack:()->Unit, onNext:()->Unit){
    val cs = MaterialTheme.colorScheme
    val tfColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = cs.primary,
        unfocusedBorderColor = cs.onSurface.copy(alpha = 0.35f),
        focusedLabelColor = cs.primary,
        cursorColor = cs.primary,
        focusedContainerColor = cs.surface,
        unfocusedContainerColor = cs.surface,
        disabledContainerColor = cs.surface,
        focusedTextColor = cs.onSurface,
        unfocusedTextColor = cs.onSurface
    )

    Scaffold(
        containerColor = cs.background,
        topBar = { BackTopBar(title = "Checkout", onBack = onBack) }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            StepHeader(active = 0)
            Spacer(Modifier.height(8.dp))

            Text(
                "Enter Shipping Details",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(12.dp))

            var fullName by remember { mutableStateOf(CheckoutStore.shipping.value.fullName) }
            var phone by remember { mutableStateOf(CheckoutStore.shipping.value.phone) }
            var province by remember { mutableStateOf(CheckoutStore.shipping.value.province) }
            var city by remember { mutableStateOf(CheckoutStore.shipping.value.city) }
            var street by remember { mutableStateOf(CheckoutStore.shipping.value.street) }
            var postal by remember { mutableStateOf(CheckoutStore.shipping.value.postal) }

            OutlinedTextField(
                fullName,
                { fullName = it },
                label = { Text("Full Name*") },
                modifier = Modifier.fillMaxWidth(),
                colors = tfColors
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                phone,
                { phone = it },
                label = { Text("Phone Number*") },
                modifier = Modifier.fillMaxWidth(),
                colors = tfColors
            )
            Spacer(Modifier.height(8.dp))

            DropdownField(
                value = province,
                options = listOf("Western", "Central", "Southern", "Northern", "Eastern"),
                label = "Select Province",
                colors = tfColors
            ) { province = it }

            Spacer(Modifier.height(8.dp))

            val cities = when (province) {
                "Western" -> listOf("Colombo", "Gampaha", "Kalutara")
                "Central" -> listOf("Kandy", "Matale", "Nuwara Eliya")
                "Southern" -> listOf("Galle", "Matara", "Hambantota")
                "Northern" -> listOf("Jaffna", "Mannar", "Vavuniya")
                "Eastern" -> listOf("Trincomalee", "Batticaloa", "Ampara")
                else -> listOf("Select province first")
            }

            DropdownField(
                value = city,
                options = cities,
                label = "Select City",
                colors = tfColors
            ) { city = it }

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                street,
                { street = it },
                label = { Text("Street Address*") },
                modifier = Modifier.fillMaxWidth(),
                colors = tfColors
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                postal,
                { postal = it },
                label = { Text("Postal Code*") },
                modifier = Modifier.fillMaxWidth(),
                colors = tfColors
            )
            Spacer(Modifier.height(16.dp))

            GradientButton(
                "Confirm",
                onClick = {
                    CheckoutStore.shipping.value = ShippingInfo(
                        fullName,
                        phone,
                        province,
                        city,
                        street,
                        postal
                    )
                    onNext()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DropdownField(
        value: String,
        options: List<String>,
        label: String,
        colors: TextFieldColors,
        onSelect: (String) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                label = { Text(label) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                colors = colors
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onSelect(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun CheckoutPaymentScreen(
        onBack: () -> Unit,
        onNext: () -> Unit
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val cs = MaterialTheme.colorScheme

        val tfColors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = cs.primary,
            unfocusedBorderColor = cs.onSurface.copy(alpha = 0.35f),
            focusedLabelColor = cs.primary,
            cursorColor = cs.primary,
            focusedContainerColor = cs.surface,
            unfocusedContainerColor = cs.surface,
            disabledContainerColor = cs.surface,
            focusedTextColor = cs.onSurface,
            unfocusedTextColor = cs.onSurface
        )

        val method = CheckoutStore.paymentMethod

        Scaffold(
            containerColor = cs.background,
            topBar = { BackTopBar(title = "Checkout", onBack = onBack) },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { padding ->
            Column(
                Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                StepHeader(active = 1)
                Spacer(Modifier.height(8.dp))

                Text(
                    "Select a Payment Method",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(Modifier.height(12.dp))

                Column {
                    RadioRow("Credit Card", method.value == PaymentMethod.CARD) {
                        method.value = PaymentMethod.CARD
                    }
                    RadioRow("PayPal", method.value == PaymentMethod.PAYPAL) {
                        method.value = PaymentMethod.PAYPAL
                    }
                    RadioRow("Google Pay", method.value == PaymentMethod.GOOGLEPAY) {
                        method.value = PaymentMethod.GOOGLEPAY
                    }
                }

                Spacer(Modifier.height(12.dp))

                if (method.value == PaymentMethod.CARD) {
                    var holder by remember { mutableStateOf(CheckoutStore.card.value.holder) }
                    var number by remember { mutableStateOf(CheckoutStore.card.value.number) }
                    var expiry by remember { mutableStateOf(CheckoutStore.card.value.expiry) }
                    var cvv by remember { mutableStateOf(CheckoutStore.card.value.cvv) }

                    OutlinedTextField(
                        holder,
                        { holder = it },
                        label = { Text("Card Holder Name") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = tfColors
                    )
                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        number,
                        { number = it },
                        label = { Text("Card Number") },
                        placeholder = { Text("4111 1111 1111 1111") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = tfColors
                    )
                    Spacer(Modifier.height(8.dp))

                    Row {
                        OutlinedTextField(
                            expiry,
                            { expiry = it },
                            label = { Text("Expiry Date") },
                            placeholder = { Text("MM/YY") },
                            modifier = Modifier.weight(1f),
                            colors = tfColors
                        )
                        Spacer(Modifier.width(12.dp))
                        OutlinedTextField(
                            cvv,
                            { cvv = it },
                            label = { Text("CVV") },
                            placeholder = { Text("123") },
                            modifier = Modifier.weight(1f),
                            colors = tfColors
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    GradientButton(
                        "Confirm",
                        onClick = {
                            CheckoutStore.card.value = CardInfo(
                                holder,
                                number,
                                expiry,
                                cvv
                            )
                            onNext()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    Spacer(Modifier.height(16.dp))
                    GradientButton(
                        "Confirm",
                        onClick = onNext,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }


    @Composable
    fun CheckoutReviewScreen(
        onBack: () -> Unit,
        onFinish: () -> Unit
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        val cs = MaterialTheme.colorScheme
        val ship = CheckoutStore.shipping.value
        val total = CartStore.total()

        Scaffold(
            containerColor = cs.background,
            topBar = { BackTopBar(title = "Checkout", onBack = onBack) },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { padding ->
            Column(
                Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                StepHeader(active = 2)
                Spacer(Modifier.height(8.dp))

                Text(
                    "Review & Confirm",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(Modifier.height(12.dp))

                Text("Shipping to: ${ship.fullName}, ${ship.street}, ${ship.city}, ${ship.province} ${ship.postal}")
                Spacer(Modifier.height(8.dp))

                Text("Payment: ${CheckoutStore.paymentMethod.value}")
                Spacer(Modifier.height(8.dp))

                Text(
                    "Items: ${CartStore.lines.size}, Total: LKR $total",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(16.dp))

                GradientButton(
                    "Place Order",
                    onClick = {
                        val id = "ORD-" + System.currentTimeMillis().toString().takeLast(6)

                        // Save summary
                        com.example.bubbleblizz.util.Orders.last.value =
                            com.example.bubbleblizz.util.OrderSummary(
                                id = id,
                                items = CartStore.lines.size,
                                total = total,
                                shippingTo = "${ship.fullName}, ${ship.city}",
                                payment = CheckoutStore.paymentMethod.value
                            )

                        CartStore.clear()

                        scope.launch {
                            snackbarHostState.showSnackbar("Order placed successfully! (#$id)")
                            delay(1000)
                            onFinish()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }


    @Composable
    private fun StepHeader(active: Int) {
        val cs = MaterialTheme.colorScheme
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    Icons.Outlined.LocalShipping,
                    contentDescription = null,
                    tint = cs.onBackground
                )
                Text("Shipping")
            }
            Divider(
                Modifier.weight(1f).padding(horizontal = 8.dp),
                color = cs.onSurface.copy(alpha = 0.3f)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    Icons.Outlined.CreditCard,
                    contentDescription = null,
                    tint = cs.onBackground
                )
                Text("Payment")
            }
            Divider(
                Modifier.weight(1f).padding(horizontal = 8.dp),
                color = cs.onSurface.copy(alpha = 0.3f)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    Icons.Outlined.ReceiptLong,
                    contentDescription = null,
                    tint = cs.onBackground
                )
                Text("Review")
            }
        }
    }

    @Composable
    private fun RadioRow(label: String, selected: Boolean, onClick: () -> Unit) {
        Row(
            Modifier.fillMaxWidth().padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary
                )
            )
            Text(label)
        }
    }
