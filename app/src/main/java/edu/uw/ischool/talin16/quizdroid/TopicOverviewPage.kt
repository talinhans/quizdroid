package edu.uw.ischool.talin16.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TopicOverviewPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview_page)
        val type = intent.getStringExtra(Constants.typeKey)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val tvNumberOfQuestions = findViewById<TextView>(R.id.tvNumberOfQuestions)

        if (type == Constants.mathVal) {
            tvDescription.text = Constants.mathsBrief
            Constants.currQuestionBank=Constants.listOfMathQuestions
        } else if (type == Constants.physicsVal) {
            tvDescription.text=Constants.physicsBrief
            Constants.currQuestionBank=Constants.listOfPhysicsQuestions
        } else if (type == Constants.marvelSHVal) {
            tvDescription.text=Constants.marvelSHBrief
            Constants.currQuestionBank=Constants.listOfMarvelSuperHeroesQuestions
        } else {
            tvDescription.text=Constants.musicBrief
            Constants.currQuestionBank=Constants.listOfMusicQuestions
        }
        tvNumberOfQuestions.text=Constants.currQuestionBank.size.toString()

        val btnBegin = findViewById<Button>(R.id.btnBegin)
        btnBegin.setOnClickListener {
            startActivity(Intent(this, QuestionPage::class.java))
            finish()
        }
    }
}