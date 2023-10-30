package com.example.katche_it.activities.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.katche_it.R
import com.example.katche_it.databinding.ActivityBottomNavigationBinding

class BottomNavigation<BottomNavigationView : View?> : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Set up item selection listener
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_back -> {
                    startActivity(Intent(this, ChooseGameActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.action_center -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    return@setOnNavigationItemSelectedListener true

                }

                R.id.action_next -> {
                    startActivity(Intent(this, ResultActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }

                else ->
                    return@setOnNavigationItemSelectedListener false
            }

        }
    }
}


