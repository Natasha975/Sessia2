package com.example.session2

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.JacksonSerializer

val supabase = createSupabaseClient(
    supabaseUrl = "https://kkvpucjozqqviytsovmc.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtrdnB1Y2pvenFxdml5dHNvdm1jIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzk3MDc1MTUsImV4cCI6MjA1NTI4MzUxNX0.qdjJLX7KYaRZUzDcfy89UgcMqMq_KtK39dbNAEMKWqI"
) {
    install(Auth)
    install(Postgrest)
    defaultSerializer = JacksonSerializer()
}
val auth = supabase.auth

suspend fun signUpUser(email: String, password: String) {
    try {
        val user = supabase.auth.signUpWith(OtpType.Email) {
            this.email = email
            this.password = password
        }
        println("User зарегистрирован: ${user.user?.email}")
    } catch (e: Exception) {
        println("Ошибка при регистрации пользователя: ${e.message}")
    }
}