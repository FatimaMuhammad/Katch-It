package com.example.katche_it.activities.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import com.example.katche_it.activities.models.Question
import com.example.katche_it.activities.models.Quiz
import com.example.katche_it.databinding.ActivityResultBinding
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    private lateinit var quiz: Quiz
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
    }

    private fun setUpViews() {
        val quizData = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)
        calculateScore()
        setAnswerView()
        setUpRewards()
    }

    private fun setUpRewards() {
        var score = 0
        for (entry in quiz.questions.entries) {
            val question: Question = entry.value
            binding.btnRewards.setOnClickListener {
                if (question.answer == question.userAnswer) {
                    // User scored more than 7 points, show congratulations toast
                    val toastText =
                        "Congratulations! You've successfully earned a reward \uD83D\uDE00\uD83D\uDE00"
                    Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
                } else {
                    // User scored 7 or less, encourage them to try again
                    val toastText = "Attempt one more quiz to win the rewards"
                    Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }


        private fun setAnswerView() {
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            binding.txtAnswer.text = Html.fromHtml(builder.toString());
        }
    }

    private fun calculateScore() {
        var score = 0
        for (entry in quiz.questions.entries) {
            val question: Question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
            }
        }
        binding.txtScore.text = "Your Score : $score"
    }
}

