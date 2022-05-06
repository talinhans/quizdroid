package edu.uw.ischool.talin16.quizdroid.Pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.talin16.quizdroid.QuizApp
import edu.uw.ischool.talin16.quizdroid.R
import edu.uw.ischool.talin16.quizdroid.others.Constants

class TopicOverviewPage : AppCompatActivity() {

    override fun onResume() {
        val repository = QuizApp.getTopicRepositoryInstance()
        val listOfAllTopics = repository.getListOfTopics()
        val type = intent.getStringExtra(Constants.typeKey)
        if (listOfAllTopics.isNotEmpty()) {
            if (type == Constants.mathVal) {
                val topic = listOfAllTopics[0]
                tvNumberOfQuestions.text = topic.questionBank.size.toString()
                Constants.currQuestionBank = topic.questionBank
                tvDescription.text = topic.longDescription
                Constants.currQuestionBank = topic.questionBank
            } else if (type == Constants.physicsVal) {
                val topic = listOfAllTopics[1]
                tvNumberOfQuestions.text = topic.questionBank.size.toString()
                Constants.currQuestionBank = topic.questionBank
                tvDescription.text = topic.longDescription
                Constants.currQuestionBank = topic.questionBank
            } else if (type == Constants.marvelSHVal) {
                val topic = listOfAllTopics[2]
                tvNumberOfQuestions.text = topic.questionBank.size.toString()
                Constants.currQuestionBank = topic.questionBank
                tvDescription.text = topic.longDescription
                Constants.currQuestionBank = topic.questionBank
            } else {
                val topic = listOfAllTopics[3]
                tvNumberOfQuestions.text = topic.questionBank.size.toString()
                Constants.currQuestionBank = topic.questionBank
                tvDescription.text = topic.longDescription
                Constants.currQuestionBank = topic.questionBank
            }
        }

        super.onResume()
    }

    private lateinit var tvDescription: TextView
    private lateinit var tvNumberOfQuestions: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview_page)
        tvDescription = findViewById<TextView>(R.id.tvDescription)
        tvNumberOfQuestions = findViewById<TextView>(R.id.tvNumberOfQuestions)
        val btnBegin = findViewById<Button>(R.id.btnBegin)
        btnBegin.setOnClickListener {
            startActivity(Intent(this, QuestionPage::class.java))
            finish()// finish this activity
        }
    }
}