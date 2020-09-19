package com.example.bballscores

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
private const val TAG = "MainActivity"
private const val REQUEST_SCORES = 0
var saved_score_a= false

class MainActivity : AppCompatActivity() {


    private lateinit var ThreePointAButton: Button
    private lateinit var TwoPointAButton: Button
    private lateinit var FreeThrowAButton: Button
    private lateinit var ThreePointBButton: Button
    private lateinit var TwoPointBButton: Button
    private lateinit var FreeThrowBButton: Button
    private lateinit var ResetButton: Button
    private lateinit var SaveButton: Button
    private lateinit var DisplayButton: Button

    private val scoresViewModel: ScoresViewModel by lazy {
        ViewModelProviders.of(this).get(ScoresViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scoresViewModel = ViewModelProviders.of(this).get(ScoresViewModel::class.java)

        ResetButton = findViewById((R.id.reset_button))
        ThreePointAButton = findViewById(R.id.A_3point)
        TwoPointAButton = findViewById(R.id.A_2point)
        FreeThrowAButton = findViewById(R.id.A_free_throw)
        ThreePointBButton = findViewById(R.id.B_3point)
        TwoPointBButton = findViewById(R.id.B_2point)
        FreeThrowBButton = findViewById(R.id.B_free_throw)
        SaveButton = findViewById(R.id.Save)
        DisplayButton = findViewById(R.id.display_button)

        ThreePointAButton.setOnClickListener { view: View ->
            scoresViewModel.threepointA()

            Team_A_Score.text = (scoresViewModel.getAscore()).toString()
        }
        TwoPointAButton.setOnClickListener { view: View ->
            scoresViewModel.twopointA()
            Team_A_Score.text = (scoresViewModel.getAscore().toString())
        }
        FreeThrowAButton.setOnClickListener { view: View ->
            scoresViewModel.freethrowA()

            Team_A_Score.text = (scoresViewModel.getAscore()).toString()

        }
        ThreePointBButton.setOnClickListener { view: View ->
            scoresViewModel.threepointB()
            Team_B_Score.text = (scoresViewModel.getBscore()).toString()
        }
        TwoPointBButton.setOnClickListener { view: View ->
            scoresViewModel.twopointB()

            Team_B_Score.text = (scoresViewModel.getBscore()).toString()
        }
        FreeThrowBButton.setOnClickListener { view: View ->
            scoresViewModel.freethrowB()
            Team_B_Score.text = (scoresViewModel.getBscore()).toString()

        }
        ResetButton.setOnClickListener { view: View ->
            scoresViewModel.reset()

            Team_B_Score.text = (scoresViewModel.getBscore()).toString()
            Team_A_Score.text = (scoresViewModel.getAscore()).toString()
        }
        SaveButton.setOnClickListener { view: View ->
            val Team_A_Save_Score = scoresViewModel.getAscore()
            val Team_B_Save_Score = scoresViewModel.getBscore()
            val intent =
                SaveActivity.newIntent(this@MainActivity, Team_A_Save_Score, Team_B_Save_Score)
            startActivityForResult(intent, REQUEST_SCORES)
        }
        DisplayButton.setOnClickListener { view: View ->

            if (saved_score_a != false) {
                Toast.makeText(this, R.string.Saved_Toast, Toast.LENGTH_SHORT).show()
            }
        }

        Team_B_Score.text = (scoresViewModel.getBscore()).toString()
        Team_A_Score.text = (scoresViewModel.getAscore()).toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_SCORES) {
            saved_score_a =
                data?.getBooleanExtra(Send_back_Scores, false) ?: false
        }
    }
}