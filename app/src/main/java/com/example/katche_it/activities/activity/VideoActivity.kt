
package com.example.katche_it.activities.activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.katche_it.databinding.ActivityVideoBinding


class VideoActivity : AppCompatActivity() {
        private lateinit var binding: ActivityVideoBinding
        private lateinit var videoView: VideoView
        private lateinit var btnSkipVideo: Button
        private lateinit var btnNext: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityVideoBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            videoView = binding.videoView
            btnSkipVideo = binding.btnSkip
            btnNext = binding.btnNext

            val videoUrl = "https://www.youtube.com/watch?v=AQcgssfr3lw" // Replace with your video URL

            videoView.setVideoURI(Uri.parse(videoUrl))
            videoView.setOnPreparedListener { mp ->
                mp.start()
                // Show Skip Video button once the video is prepared
                btnSkipVideo.visibility = View.VISIBLE
            }

            btnSkipVideo.setOnClickListener {
                // Handle the skip video button click
                finish() // Close the VideoActivity
            }

            videoView.setOnCompletionListener {
                // Show Next button after video playback is complete
                btnNext.visibility = View.VISIBLE
            }

            btnNext.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                //startActivity(Intent(this@VideoActivity, QuestionActivity::class.java))
                //finish()
            }
        }
    }
