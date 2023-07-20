package com.example.basketballscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basketballscore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var localScore: Int = binding.localBoard.text.toString().toInt()
        var visitorScore: Int = binding.visitorBoard.text.toString().toInt()

        binding.localButtonToSubtractPoint.setOnClickListener {
            localScore = localScore - 1
            if (localScore <= 0) localScore = 0
            binding.localBoard.text = localScore.toString()
        }

        binding.localButtonToAddOnePoint.setOnClickListener {
            localScore++
            binding.localBoard.text = localScore.toString()
        }

        binding.localButtonToAddTwoPoints.setOnClickListener {
            localScore = localScore + 2
            binding.localBoard.text = localScore.toString()
        }

        binding.visitorButtonToSubtractPoint.setOnClickListener {
            visitorScore = visitorScore - 1
            if (visitorScore <= 0) visitorScore = 0
            binding.visitorBoard.text = visitorScore.toString()
        }

        binding.visitorButtonToAddOnePoint.setOnClickListener {
            visitorScore++
            binding.visitorBoard.text = visitorScore.toString()
        }

        binding.visitorButtonToAddTwoPoints.setOnClickListener {
            visitorScore = visitorScore + 2
            binding.visitorBoard.text = visitorScore.toString()
        }

        binding.buttonRestartPoints.setOnClickListener{
            visitorScore = 0
            localScore = 0
            binding.visitorBoard.text = visitorScore.toString()
            binding.localBoard.text = localScore.toString()
        }
        binding.buttonToScoreActivity.setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra(ScoreActivity.LOCAL_SCORE_KEY, localScore)
            intent.putExtra(ScoreActivity.VISITOR_SCORE_KEY, visitorScore)
            startActivity(intent)
        }
    }
}