package com.example.bubbleblizz.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.bubbleblizz.ui.component.BackTopBar
import com.example.bubbleblizz.ui.component.GradientButton

// LOGIN SCREEN
@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onRegister: () -> Unit,
    onBack: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        BackTopBar(title = "Login", onBack = onBack)

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        // Enable scrolling for smaller screens
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(16.dp))
            GradientButton(
                text = "Login",
                onClick = onLogin,
                modifier = Modifier.fillMaxWidth()
            )
            TextButton(
                onClick = onRegister,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Create account",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

// REGISTER SCREEN
@Composable
fun RegisterScreen(
    onRegistered: () -> Unit,
    onBack: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        BackTopBar(title = "Register", onBack = onBack)

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        // Enable scrolling for smaller screens
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(16.dp))
            GradientButton(
                text = "Sign up",
                onClick = onRegistered,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
