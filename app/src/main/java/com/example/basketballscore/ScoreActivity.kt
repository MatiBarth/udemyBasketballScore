package com.example.basketballscore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basketballscore.databinding.ActivityMainBinding
import com.example.basketballscore.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding
    private var localScore: Int = 0
    private var visitorScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        localScore = intent.getIntExtra(LOCAL_SCORE_KEY,0)
        visitorScore = intent.getIntExtra(VISITOR_SCORE_KEY,0)

        binding.scoreResult.text = localScore.toString() + " - " + visitorScore.toString()
        finalResult()
    }

    private fun finalResult() {
        if(localScore>visitorScore) binding.finalResult.text = getString(R.string.string_victoria,"local")
        else if(localScore<visitorScore) binding.finalResult.text = getString(R.string.string_victoria,"visitante")
        else binding.finalResult.text = getString(R.string.string_empate)
    }

    companion object{
        const val LOCAL_SCORE_KEY = "localScore"
        const val VISITOR_SCORE_KEY = "visitorScore"
    }
}