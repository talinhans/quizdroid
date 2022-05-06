package edu.uw.ischool.talin16.quizdroid.Pages

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.talin16.quizdroid.MainActivity
import edu.uw.ischool.talin16.quizdroid.R
import edu.uw.ischool.talin16.quizdroid.others.Constants

class ResultPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        var tvScore = findViewById<TextView>(R.id.tvScore)
        tvScore.text =
            "You have ${Constants.numOfCorrectlyAnsweredQues} out of ${Constants.totalNumberOfQuestions} Correct!"


        var correctAns: String = ""
        if (Constants.lastQuestion.correctOption == 1) {
            correctAns = Constants.lastQuestion.op1
        } else if (Constants.lastQuestion.correctOption == 2) {
            correctAns = Constants.lastQuestion.op2
        } else if (Constants.lastQuestion.correctOption == 3) {
            correctAns = Constants.lastQuestion.op3
        } else {
            correctAns = Constants.lastQuestion.op4
        }

        var tvQuestion = findViewById<TextView>(R.id.tvResultPageQuestion)
        tvQuestion.text = "Question : ${Constants.lastQuestion.question}"
        var tvCorrectAnswer = findViewById<TextView>(R.id.tvResultPageCorrectAns)
        tvCorrectAnswer.text = "Correct Answer : $correctAns"
        var tvUsersAnswer = findViewById<TextView>(R.id.tvResultPageUsersAnswer)
        tvUsersAnswer.text = "Your Answer : ${Constants.lastUsersAnswer}"
        var btnNextOrFinish = findViewById<TextView>(R.id.btnNextOrFinish)

        if (Constants.isLastQuestionAnswered) {
            btnNextOrFinish.text = "Finish"
        }

        btnNextOrFinish.setOnClickListener {
            val intent: Intent
            if (Constants.isLastQuestionAnswered) {
                Constants.clearData()
                intent = Intent(this, MainActivity::class.java)
            } else {
                intent = Intent(this, QuestionPage::class.java)
            }
            startActivity(intent)
            finish()// finish this activity
        }
    }
}