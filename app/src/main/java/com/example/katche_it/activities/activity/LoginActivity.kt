package com.example.katche_it.activities.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.katche_it.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogIn.setOnClickListener {
            login()

        }
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.forgotpass.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login() {
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPassword.text.toString()
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Email and Password can't be Empty", Toast.LENGTH_SHORT)
                .show()
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, " Successfully Login", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, UserBrandActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            }

    }

}