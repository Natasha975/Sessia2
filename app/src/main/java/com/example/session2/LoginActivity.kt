package com.example.session2

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var rememberPasswordCheckBox: CheckBox
    private lateinit var forgotPasswordTextView: TextView
    private lateinit var logInButton: Button
    private lateinit var signUpTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        emailEditText = findViewById<EditText>(R.id.textInputLayoutEmailAddress) as TextInputEditText
        passwordEditText = findViewById<EditText>(R.id.textInputLayoutPassword) as TextInputEditText
        rememberPasswordCheckBox = findViewById(R.id.checkBoxRememberPassword)
        forgotPasswordTextView = findViewById(R.id.textViewForgotPassword)
        logInButton = findViewById(R.id.buttonLogIn)
        signUpTextView = findViewById(R.id.textViewSignUp)

        logInButton.setOnClickListener {
            signInUser()
        }

        signUpTextView.setOnClickListener {
            Toast.makeText(this, "Sign Up clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}