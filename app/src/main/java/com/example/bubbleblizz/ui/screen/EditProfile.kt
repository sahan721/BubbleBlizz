package com.example.bubbleblizz.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bubbleblizz.ui.component.BackTopBar
import com.example.bubbleblizz.ui.component.GradientButton
import com.example.bubbleblizz.util.UserProfile
import com.example.bubbleblizz.util.UserStore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(onBack: () -> Unit) {
    val profileState = UserStore.profile
    var first by remember { mutableStateOf(profileState.value.firstName) }
    var last by remember { mutableStateOf(profileState.value.lastName) }
    var phone by remember { mutableStateOf(profileState.value.phone) }
    var gender by remember { mutableStateOf(profileState.value.gender) }
    var dob by remember { mutableStateOf(profileState.value.dob) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        BackTopBar(title = "Edit Profile", onBack = onBack)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = profileState.value.email,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(Modifier.height(16.dp))

            // --- Reusable TextField Style ---
            val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                cursorColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.surface
            )

            OutlinedTextField(
                value = first,
                onValueChange = { first = it },
                label = { Text("What’s your first name?") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = last,
                onValueChange = { last = it },
                label = { Text("And your last name?") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone number") },
                leadingIcon = { Icon(Icons.Outlined.Phone, contentDescription = null) },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = gender,
                onValueChange = { gender = it },
                label = { Text("Select your gender") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = dob,
                onValueChange = { dob = it },
                label = { Text("What is your date of birth?") },
                leadingIcon = { Icon(Icons.Outlined.Cake, contentDescription = null) },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            GradientButton(
                text = "Update Profile",
                onClick = {
                    UserStore.update(
                        UserProfile(first, last, profileState.value.email, phone, gender, dob)
                    )
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
