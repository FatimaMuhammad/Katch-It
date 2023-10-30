package com.example.katche_it.activities.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.katche_it.databinding.ActivityChooseGameBinding

class ChooseGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          binding = ActivityChooseGameBinding.inflate(layoutInflater)
                val view = binding.root
                setContentView(view)

                // Access UI elements using binding
                val playerNameButton = binding.Quiz
                val brandNameButton = binding.Puzzle

                binding.Quiz.setOnClickListener {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }

                binding.Puzzle.setOnClickListener {

                    val intent = Intent(this, PuzzleGameActivity::class.java)
                    startActivity(intent)
                }
            }
        }


