package com.example.session2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val fullNameEditText = findViewById<EditText>(R.id.textInputLayoutFullName)
    val phoneNumberEditText = findViewById<EditText>(R.id.textInputLayoutPhoneNumber)
    val emailEditText = findViewById<EditText>(R.id.textInputLayoutEmailAddress)
    val passwordEditText = findViewById<EditText>(R.id.textInputLayoutPassword)
    val confirmPasswordEditText = findViewById<EditText>(R.id.textInputLayoutConfirmPassword)
    val termsCheckBox = findViewById<CheckBox>(R.id.checkBoxTerms)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val signUpButton = findViewById<Button>(R.id.buttonSignUp)

        signUpButton.setOnClickListener {
            signUpUser()
        }
    }
    private fun signUpUser() {
        val fullName = fullNameEditText.text.toString().trim()
        val phoneNumber = phoneNumberEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val confirmPassword = confirmPasswordEditText.text.toString().trim()
        val termsAccepted = termsCheckBox.isChecked

        if (fullName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            return
        }

        if (!termsAccepted) {
            Toast.makeText(this, "Пожалуйста, примите настоящие правила и условия", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            try {
                SupabaseMan.signUp(email, password, phoneNumber, fullName)

                Toast.makeText(this@MainActivity, "Зарегистрируйтесь успешно!", Toast.LENGTH_SHORT).show()
                Log.d("Регистрация", "Успешно прошла регистрация по электронной почте: $email")
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Sign up failed: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("Регистрация", "Ошибка при регистрации", e)
            }
        }
    }
}