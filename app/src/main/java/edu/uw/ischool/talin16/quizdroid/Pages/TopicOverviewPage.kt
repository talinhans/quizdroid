package edu.uw.ischool.talin16.quizdroid.Pages

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.talin16.quizdroid.R
import edu.uw.ischool.talin16.quizdroid.QuizApp
import edu.uw.ischool.talin16.quizdroid.others.Constants

class TopicOverviewPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview_page)
        val type = intent.getStringExtra(Constants.typeKey)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val tvNumberOfQuestions = findViewById<TextView>(R.id.tvNumberOfQuestions)

        val repository = QuizApp.getTopicRepositoryInstance()
        val listOfAllTopics = repository.getListOfTopics()

        if (type == Constants.mathVal) {
            val topic = listOfAllTopics[0]
            tvDescription.text = topic.longDescription
            Constants.currQuestionBank = topic.questionBank
        } else if (type == Constants.physicsVal) {
            val topic = listOfAllTopics[1]
            tvDescription.text = topic.longDescription
            Constants.currQuestionBank = topic.questionBank
        } else if (type == Constants.marvelSHVal) {
            val topic = listOfAllTopics[2]
            tvDescription.text = topic.longDescription
            Constants.currQuestionBank = topic.questionBank
        } else {
            val topic = listOfAllTopics[3]
            tvDescription.text = topic.longDescription
            Constants.currQuestionBank = topic.questionBank
        }

        tvNumberOfQuestions.text = Constants.currQuestionBank.size.toString()

        val btnBegin = findViewById<Button>(R.id.btnBegin)
        btnBegin.setOnClickListener {
            startActivity(Intent(this, QuestionPage::class.java))
            finish()// finish this activity
        }
    }
}