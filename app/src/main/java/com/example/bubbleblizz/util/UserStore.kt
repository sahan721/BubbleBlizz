package com.example.bubbleblizz.util
import androidx.compose.runtime.mutableStateOf
data class UserProfile(
    var firstName: String = "CustomerUser",
    var lastName: String = "1",
    var email: String = "CustomerUser1@gmail.com",
    var phone: String = "+94 71 234 5678",
    var gender: String = "Prefer not to say",
    var dob: String = "2000-01-01"
)
object UserStore {
    val profile = mutableStateOf(UserProfile())
    fun update(p: UserProfile) { profile.value = p }
}