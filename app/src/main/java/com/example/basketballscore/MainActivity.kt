package com.example.basketballscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.basketballscore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.localScore.observe(this, Observer { localScoreValue: Int ->
            binding.localBoard.text = localScoreValue.toString()
        })

        viewModel.visitorScore.observe(this, Observer { visitorScoreValue: Int ->
            binding.visitorBoard.text = visitorScoreValue.toString()
        })

        binding.localButtonToSubtractPoint.setOnClickListener {
            subtractPoint(1, localTeam = true)
        }

        binding.localButtonToAddOnePoint.setOnClickListener {
            addPoint(1, localTeam = true)
        }

        binding.localButtonToAddTwoPoints.setOnClickListener {
            addPoint(2, localTeam = true)
        }

        binding.visitorButtonToSubtractPoint.setOnClickListener {
            subtractPoint(1, localTeam = false)
        }

        binding.visitorButtonToAddOnePoint.setOnClickListener {
            addPoint(1, false)
        }

        binding.visitorButtonToAddTwoPoints.setOnClickListener {
            addPoint(2, false)
        }

        binding.buttonRestartPoints.setOnClickListener {
            resetPoints()
        }
        binding.buttonToScoreActivity.setOnClickListener {
            startScoreActivity()
        }
    }

    private fun startScoreActivity() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra(ScoreActivity.LOCAL_SCORE_KEY, viewModel.localScore.value)
        intent.putExtra(ScoreActivity.VISITOR_SCORE_KEY, viewModel.visitorScore.value)
        startActivity(intent)
    }

    private fun resetPoints() {
        viewModel.resetPoints()
    }

    private fun addPoint(point: Int, localTeam: Boolean) {
        viewModel.addPoint(point, localTeam)
    }

    private fun subtractPoint(point: Int, localTeam: Boolean) {
        viewModel.subtractPoint(point, localTeam)
    }
}