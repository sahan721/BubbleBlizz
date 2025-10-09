package com.example.bubbleblizz.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bubbleblizz.ui.assets.Images
import com.example.bubbleblizz.ui.component.BubbleBottomBar
import com.example.bubbleblizz.ui.component.GradientHeader
import com.example.bubbleblizz.ui.component.BackTopBar
import com.example.bubbleblizz.util.CartStore

@Composable
fun CartScreen(
    onCheckout: () -> Unit,
    onBack: () -> Unit,
    onGoHome: () -> Unit,
    onGoFavorites: () -> Unit,
    onGoSettings: () -> Unit
) {
    var search by remember { mutableStateOf("") }
    var tab by remember { mutableStateOf(1) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BubbleBottomBar(
                selected = tab,
                onSelect = {
                    tab = it
                    when (it) {
                        0 -> onGoHome()
                        1 -> Unit
                        2 -> onGoFavorites()
                        3 -> onGoSettings()
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            GradientHeader(search = search, onSearch = { search = it })
            BackTopBar(title = "My Cart", onBack = onBack)

            // make it Scrollable
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                for (line in CartStore.lines) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(
                                Images.ofName("img_${line.id}")
                            ),
                            contentDescription = line.name,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(end = 12.dp)
                        )
                        Column(Modifier.weight(1f)) {
                            Text(line.name, style = MaterialTheme.typography.titleMedium)
                            Text(line.sub)
                            Text("LKR ${line.price}")
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            TextButton(onClick = {
                                if (line.qty > 1) CartStore.updateQty(line.id, line.qty - 1)
                            }) { Text("−") }
                            Text(line.qty.toString(), modifier = Modifier.padding(horizontal = 8.dp))
                            TextButton(onClick = { CartStore.updateQty(line.id, line.qty + 1) }) { Text("+") }
                        }
                    }
                    OutlinedButton(
                        onClick = { CartStore.remove(line.id) },
                        modifier = Modifier.align(Alignment.End)
                    ) { Text("Remove") }
                    Divider()
                }

                Spacer(Modifier.height(20.dp))
                Divider()
                Spacer(Modifier.height(10.dp))

                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = onCheckout,
                        modifier = Modifier
                            .width(240.dp)
                            .padding(bottom = 12.dp)
                    ) {
                        Text("Checkout")
                    }
                }
            }
        }
    }
}
