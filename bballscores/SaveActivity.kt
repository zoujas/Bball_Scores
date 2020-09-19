package com.example.bballscores

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

const val Send_back_Scores= "com.example.bballscores.send_back_scores"

private const val Team_A_Save_score = "com.example.bballscores.team_A_score"
private const val Team_B_Save_Score = "com.example.bballscores.team_B_score"


class SaveActivity : AppCompatActivity() {
    var save_clicked = false;
    private var Team_A_scores = 0
    private var Team_B_scores = 0
    private lateinit var showScoreButton: Button
    private lateinit var teamAscoreTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)
        Team_A_scores = intent.getIntExtra(Team_A_Save_score,0)
        Team_B_scores= intent.getIntExtra(Team_B_Save_Score,0)
        showScoreButton = findViewById(R.id.teamAbutton)
        teamAscoreTextView = findViewById(R.id.ASave)

        showScoreButton.setOnClickListener{
            teamAscoreTextView.text = Team_A_scores.toString()

            Toast.makeText(this,R.string.Game_Saved_Toast,Toast.LENGTH_SHORT).show()
            showScoreButton.isClickable= false
            save_clicked= true
        }
        returnTheAnswers(save_clicked)
    }

    private fun returnTheAnswers(clicked:Boolean){
        val data = Intent().apply{
            putExtra(Send_back_Scores,clicked)

        }
        setResult(Activity.RESULT_OK,data)
    }
    companion object {

        fun newIntent (packageContext: Context, Team_A_scores:Int, Team_B_scores:Int ): Intent {
            return Intent(packageContext, SaveActivity::class.java).apply{
                putExtra(Team_A_Save_score,Team_A_scores)
                putExtra(Team_B_Save_Score,Team_B_scores)
            }
        }
    }
}
