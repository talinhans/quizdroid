package edu.uw.ischool.talin16.quizdroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionPage : AppCompatActivity() {

    lateinit var btnSubmit: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var tvQuestion: TextView
    private lateinit var currentQuestion: QuestionModel
    var selectedOption = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_page)

        radioGroup = findViewById<RadioGroup>(R.id.myRadioGroup)
        btnSubmit = findViewById<Button>(R.id.btnSubmit)
        tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        btnSubmit.isEnabled = false

        Constants.totalNumberOfQuestions = Constants.currQuestionBank.size

        setupQnA(Constants.currQuestionBank[Constants.currIdx])// starts with i=0

        btnSubmit.setOnClickListener {
            Constants.lastQuestion = currentQuestion
            Constants.lastUsersAnswer = selectedOption
            if (currentQuestion.correctAns == selectedOption.trim()) {
                Constants.numOfCorrectlyAnsweredQues += 1
            }
            Constants.currIdx += 1;

            //setupQNA for NextQuestion
            startActivity(Intent(this, ResultPage::class.java))
        }
        setupRadioGroupListener()
    }

    private fun setupRadioGroupListener() {
        radioGroup.setOnCheckedChangeListener { _, i ->
            val selectedRB = findViewById<RadioButton>(i)
            if (selectedRB != null) {
                if (Constants.currIdx == (Constants.currQuestionBank.size - 1)) {
                    Constants.isLastQuestionAnswered = true
                }
                btnSubmit.isEnabled = true
                selectedOption = selectedRB.text.toString()
            }
        }
    }

    private fun setupQnA(Question: QuestionModel) {// setup radio button
        currentQuestion = Question
        val shuffledListOfOptions = Question.Options.shuffled()
        tvQuestion.text = "Q: ${Question.question}"
        for (i in 0 until radioGroup.childCount) {
            (radioGroup.getChildAt(i) as RadioButton).text = shuffledListOfOptions[i]
        }
    }
}

