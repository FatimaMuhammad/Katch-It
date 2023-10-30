package com.example.katche_it.activities.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.katche_it.activities.models.Question
import com.example.katche_it.activities.models.Quiz
import com.example.katche_it.databinding.ActivityResultBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var quiz: Quiz
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()

        auth = FirebaseAuth.getInstance()
        binding.textEmail.text = auth.currentUser?.email
    }



    private fun setUpViews() {
        val quizData = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)
        calculateScore()
        //setAnswerView()
        setUpRewards()
    }

    private fun setUpRewards() {
       // var score = 0
        for (entry in quiz.questions.entries) {
            val question: Question = entry.value
            binding.btnRewards.setOnClickListener {
                if (question.answer == question.userAnswer) {
                    val toastText = "Congratulations! You've successfully earned a reward \uD83D\uDE00\uD83D\uDE00"
                    Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
                    // User scored more than 7 points, show congratulations toast
                    val intent = Intent(this, CouponActivity::class.java)
                    startActivity(intent)
                    finish()
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


       /* private fun setAnswerView() {
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
    }*/

    private fun calculateScore() {
        var score = 0
        for (entry in quiz.questions.entries) {
            val question: Question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
            }
        }
        binding.txtScore.text = "Your Score : 70/$score"
    }
}

