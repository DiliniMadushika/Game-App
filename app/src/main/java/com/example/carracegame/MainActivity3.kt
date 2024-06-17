package com.example.carracegame

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity3: AppCompatActivity(),GameTask {
    lateinit var rootLayout :LinearLayout
    lateinit var startBtn : Button
    lateinit var imageView4 : ImageView
    lateinit var mGameView: GameView
    lateinit var score:TextView
    lateinit var highScoreTextView: TextView
    lateinit var sharedPreferences: SharedPreferences
     var highScore = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        startBtn = findViewById(R.id.startBtn)
        rootLayout = findViewById(R.id.rootLayout)
        imageView4 = findViewById((R.id.imageView4))
        score = findViewById(R.id.score)
        highScoreTextView = findViewById(R.id.highscore)

        mGameView = GameView(this,this)
        sharedPreferences = getSharedPreferences("GamePrefs", Context.MODE_PRIVATE)

        // Other initialization code...

        // Retrieve and display the high score
        highScore = sharedPreferences.getInt("highScore", 0)
        updateHighestScore(highScore)

        // Start button click listener...


        updateHighestScore(highScore)





        startBtn.setOnClickListener {
            mGameView.setBackgroundResource(R.drawable.road3)
            rootLayout.addView(mGameView)
            startBtn.visibility = View.GONE
            imageView4.visibility = View.GONE
            score.visibility = View.GONE



        }
    }

    override fun closeGame(mScore: Int) {
        score.text = "Score : $mScore"
        rootLayout.removeView(mGameView)
        startBtn.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
        imageView4.visibility = View.VISIBLE

        startBtn.setOnClickListener {

            score.text = "Score : 0"
            mGameView.resetGame()


            mGameView.setBackgroundResource(R.drawable.road3)
            rootLayout.addView(mGameView)
            startBtn.visibility = View.GONE
            imageView4.visibility = View.GONE
            score.visibility = View.GONE

        }


        if (mScore > highScore) {
            highScore = mScore
            updateHighestScore(highScore)
        }

        if (mScore > 0) {

            mGameView = GameView(this, this)
            startBtn.setOnClickListener {
                mGameView.setBackgroundResource(R.drawable.road3)
                rootLayout.addView(mGameView)
                startBtn.visibility = View.GONE
                score.visibility = View.GONE
                imageView4.visibility = View.GONE


            }
        }
    }


    internal fun updateHighestScore(newScore: Int) {
        // Update the UI with the new high score
        highScoreTextView.text = "Highest Score: $newScore"

        // Save the new high score in SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putInt("highScore", newScore)
        editor.apply()


    }





}