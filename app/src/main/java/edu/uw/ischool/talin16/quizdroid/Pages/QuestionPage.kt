package edu.uw.ischool.talin16.quizdroid.Pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.talin16.quizdroid.R
import edu.uw.ischool.talin16.quizdroid.models.Quiz
import edu.uw.ischool.talin16.quizdroid.others.Constants

class QuestionPage : AppCompatActivity() {

    lateinit var btnSubmit: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var tvQuestion: TextView
    private lateinit var currentQuestion: Quiz
    var selectedOption = ""
    lateinit var radioBtn1: RadioButton
    lateinit var radioBtn2: RadioButton
    lateinit var radioBtn3: RadioButton
    lateinit var radioBtn4: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_page)

        radioBtn1 = findViewById<RadioButton>(R.id.radioButtonOp1)
        radioBtn2 = findViewById<RadioButton>(R.id.radioButtonOp2)
        radioBtn3 = findViewById<RadioButton>(R.id.radioButtonOp3)
        radioBtn4 = findViewById<RadioButton>(R.id.radioButtonOp4)

        radioGroup = findViewById<RadioGroup>(R.id.myRadioGroup)
        btnSubmit = findViewById<Button>(R.id.btnSubmit)
        tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        btnSubmit.isEnabled = false

        Constants.totalNumberOfQuestions = Constants.currQuestionBank.size

        setupQnA(Constants.currQuestionBank[Constants.currIdx])// starts with i=0

        btnSubmit.setOnClickListener {
            Constants.lastQuestion = currentQuestion
            Constants.lastUsersAnswer = selectedOption

            checkAns()

            Constants.currIdx += 1;

            //setupQNA for NextQuestion
            startActivity(Intent(this, ResultPage::class.java))
        }

        setupRadioGroupListener()
    }

    private fun checkAns() {
        var correctAns = ""
        if (currentQuestion.correctOption == 1) {
            correctAns = radioBtn1.text.toString()
        } else if (currentQuestion.correctOption == 2) {
            correctAns = radioBtn2.text.toString()
        } else if (currentQuestion.correctOption == 3) {
            correctAns = radioBtn3.text.toString()
        } else {
            correctAns = radioBtn4.text.toString()
        }

        if (correctAns == selectedOption.trim()) {
            Constants.numOfCorrectlyAnsweredQues += 1

        }
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

    private fun setupQnA(Question: Quiz) {// setup radio button
        currentQuestion = Question
        tvQuestion.text = "Q: ${Question.question}"
        radioBtn1.text = Question.op1
        radioBtn2.text = Question.op2
        radioBtn3.text = Question.op3
        radioBtn4.text = Question.op4
    }
}



//        for (i in 0 until radioGroup.childCount) {
//            (radioGroup.getChildAt(i) as RadioButton).text = Question.op1
//        }