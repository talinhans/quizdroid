package edu.uw.ischool.talin16.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        var tvScore = findViewById<TextView>(R.id.tvScore)
        tvScore.text =
            "You have ${Constants.numOfCorrectlyAnsweredQues} out of ${Constants.totalNumberOfQuestions} Correct!"

        var tvQuestion = findViewById<TextView>(R.id.tvResultPageQuestion)
        tvQuestion.text = "Question : ${Constants.lastQuestion.question}"
        var tvCorrectAnswer = findViewById<TextView>(R.id.tvResultPageCorrectAns)
        tvCorrectAnswer.text = "Correct Answer : ${Constants.lastQuestion.correctAns}"
        var tvUsersAnswer = findViewById<TextView>(R.id.tvResultPageUsersAnswer)
        tvUsersAnswer.text = "Your Answer : ${Constants.lastUsersAnswer}"
        var btnNextOrFinish = findViewById<TextView>(R.id.btnNextOrFinish)

        if (Constants.isLastQuestionAnswered) {
            btnNextOrFinish.text = "Finish"
        }

        btnNextOrFinish.setOnClickListener {
            val intent: Intent
            if (Constants.isLastQuestionAnswered) {
               // remove all previous data
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