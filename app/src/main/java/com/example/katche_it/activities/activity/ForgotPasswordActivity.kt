package com.example.katche_it.activities.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.katche_it.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.goback.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnpass.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnReset.setOnClickListener{
         val email: String = binding.etForgotEmail.text.toString().trim{ it <= ' '}
            if (email.isBlank()) {
                Toast.makeText(this, "Email can't be Empty", Toast.LENGTH_SHORT)
                    .show()

           }else{
               FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                   .addOnCompleteListener{task ->
                       if (task.isSuccessful) {
                           Toast.makeText(this, " Email sent Successfully to reset Password",
                               Toast.LENGTH_SHORT).show()
                           finish()

                       } else {
                           Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                       }
                   }
            }
            }

        }
    }

    /*private fun forgotPass(emailInfo: ActionCodeEmailInfo) {

    }*/
