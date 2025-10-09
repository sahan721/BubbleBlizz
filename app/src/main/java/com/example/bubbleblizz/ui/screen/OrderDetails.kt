
package com.example.bubbleblizz.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bubbleblizz.ui.component.BackTopBar
import com.example.bubbleblizz.ui.component.GradientButton
import com.example.bubbleblizz.util.Orders

@Composable
fun OrderDetailsScreen(onBack:()->Unit, onBackToCart:()->Unit){
    val order = Orders.last.value
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { BackTopBar(title = "Order Details", onBack = onBack) }
    ){ padding ->
        Column(Modifier.padding(padding).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Order Successful!", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
            Spacer(Modifier.height(8.dp))
            Text("Thank you for your purchase. Your order has been placed and is now being processed.", textAlign = TextAlign.Center)
            Spacer(Modifier.height(24.dp))
            if (order != null) {
                ElevatedCard(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Order ID: ${order.id}", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(6.dp))
                        Text("Items: ${order.items}")
                        Text("Total: LKR ${order.total}")
                        Text("Payment: ${order.payment}")
                        Spacer(Modifier.height(6.dp))
                        Text("Shipping to: ${order.shippingTo}")
                    }
                }
            } else {
                Text("No order information found.")
            }
            Spacer(Modifier.height(24.dp))
            GradientButton(text = "Back to Cart", onClick = onBackToCart, modifier = Modifier.fillMaxWidth())
        }
    }
}
