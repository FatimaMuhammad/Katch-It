package com.example.katche_it.activities.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.katche_it.databinding.ActivityUserBrandBinding

class UserBrandActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBrandBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityUserBrandBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            // Access UI elements using binding
            val playerNameButton = binding.player
            val brandNameButton = binding.brand

            binding.player.setOnClickListener {
                val intent = Intent(this, DisplayProductsActivity::class.java)
                startActivity(intent)
            }

            binding.brand.setOnClickListener {

                val intent = Intent(this, BrandActivity::class.java)
                startActivity(intent)
            }
        }
    }

