package com.example.bballscores

import android.util.Log
import androidx.lifecycle.ViewModel
private const val TAG = "ScoresViewModel"
class ScoresViewModel : ViewModel() {

    private val TeamAPoints = Scores(R.string.Team_A_Score, 0)
    private val TeamBPoints = Scores(R.string.Team_B_Score, 0)
    var TeamAScore = 0
    var TeamBScore = 0

    val currentPointsA: Int
        get() = TeamAPoints.score

    val currentPointsB: Int
        get() = TeamBPoints.score

    fun getAscore():Int{
        return currentPointsA
    }
    fun getBscore():Int{
        return currentPointsB
    }
    fun reset(){
        TeamAPoints.score = 0
        TeamBPoints.score = 0
    }
    fun threepointA(){
        TeamAPoints.score = TeamAPoints.score+3
    }
    fun twopointA(){
        TeamAPoints.score = TeamAPoints.score+2
    }
    fun freethrowA(){
        TeamAPoints.score = TeamAPoints.score +1
    }
    fun threepointB(){
        TeamBPoints.score = TeamBPoints.score+3
    }
    fun twopointB(){
        TeamBPoints.score = TeamBPoints.score+2
    }
    fun freethrowB(){
        TeamBPoints.score = TeamBPoints.score +1
    }


}