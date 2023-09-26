package com.example.katche_it.activities.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.katche_it.R

class SplashScreenActivity : AppCompatActivity() {

    private val Splash_Delay: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        // Use a Handler to delay the opening of the main activity
        supportActionBar?.hide()

        Handler().postDelayed({
            // Create an Intent to start the main activity
            val intent = Intent(this@SplashScreenActivity, LoginIntroActivity::class.java)
            startActivity(intent)
            finish()
        }, Splash_Delay)
    }
}