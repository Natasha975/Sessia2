package com.example.session2

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.serializer.JacksonSerializer

class SupabaseMan{
    val supabase = createSupabaseClient(
        supabaseUrl = "https://kkvpucjozqqviytsovmc.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtrdnB1Y2pvenFxdml5dHNvdm1jIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzk3MDc1MTUsImV4cCI6MjA1NTI4MzUxNX0.qdjJLX7KYaRZUzDcfy89UgcMqMq_KtK39dbNAEMKWqI"
    ) {
        install(Auth)
        install(Postgrest)
        defaultSerializer = JacksonSerializer()
    }

    suspend fun signIn(mail: String,pass: String) {
        supabase.auth.signInWith(Email){
            email = mail
            password = pass
        }
    }

    suspend fun signUp(mail: String, pass: String, phone: String, name:String) {

        supabase.auth.signUpWith(Email){
            email = mail
            password = pass
        }
        val user = supabase.auth.sessionManager.loadSession()?.user?.id

        val newUser = UserModel(user!!, balance = 0.00f, is_courier = false, name = name, phone_number = phone, created_at = now())
        supabase.from("users").insert(newUser)

    }
}

data class UserModel(
    val id: String,
    val balance: Float,
    val is_courier: Boolean,
    val name: String,
    val phone_number: String,
    val created_at: String
)
