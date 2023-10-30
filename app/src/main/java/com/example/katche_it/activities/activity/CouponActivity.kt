package com.example.katche_it.activities.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.katche_it.activities.models.Quiz
import com.example.katche_it.databinding.ActivityCouponBinding
import com.google.firebase.auth.FirebaseAuth

class CouponActivity : AppCompatActivity() {
    private lateinit var quiz: Quiz
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityCouponBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCouponBinding.inflate(layoutInflater)
        setContentView(binding.root)
            //setUpViews()

        auth = FirebaseAuth.getInstance()
        binding.textEmail.text = auth.currentUser?.email
    }

    /* private fun setUpViews() {
        val quizData = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)
        calculateScore()
    }

    private fun calculateScore() {
        var score = 0
        for (entry in quiz.questions.entries) {
            val question: Question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
            }
        }
        binding.textScore.text = "Your Score : 70/$score"
    }
}*/
}