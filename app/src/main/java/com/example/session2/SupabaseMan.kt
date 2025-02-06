package com.example.session2

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.serializer.JacksonSerializer

val supabase = createSupabaseClient(
    supabaseUrl = "",
    supabaseKey = ""
) {
    //  install(Auth)
    install(Postgrest)
    //install other modules
    defaultSerializer = JacksonSerializer()
}
    suspend fun GetMain(): List<Main> {
        return supabase.from("Per").select().decodeList<Main>()
    }

    suspend fun PostMain(id: Int, Name: String, Photo: String, Birthday: String){
        supabase.from("Per").insert(mapOf(
            "id" to id,
            "Name" to Name,
            "Photo" to Photo,
            "Birthday" to Birthday
        ))
    }
//suspend fun signUp(mail: String, pass: String, phone: String, name:String) {
//    supa.auth.signUpWith(Email){
//        email = mail
//        password = pass
//    }
//    val user = supa.auth.sessionManager.loadSession()?.user?.id
//
//    val newUser = UserModel(user!!, balance = 0.00f, is_courier = false, name = name, phone_number = phone, created_at = now())
//    supa.from("users").insert(newUser)
//
//}

data class Main(
    val id:Int,
    val Name:String,
    val Photo: String,
    val Birthday: String
)
