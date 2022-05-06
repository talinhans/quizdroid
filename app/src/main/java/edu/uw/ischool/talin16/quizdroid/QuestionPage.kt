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
        // code this in On Resume , it Will be better I guess
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
                Log.d("Current", "Inside if , user selected correct option")
                Constants.numOfCorrectlyAnsweredQues += 1

//                Constants.listOfAnswers.add(
//                    AnswerModel(
//                        currentQuestion,
//                        selectedOption,
//                        true
//                    )
//                )
            } else {
                Log.d("Current", "Else, user selected wrong option")
//                Constants.listOfAnswers.add(
//                    AnswerModel(
//                        currentQuestion,
//                        selectedOption,
//                        false
//                    )
//                )
            }
            Constants.currIdx += 1;
            //check here if user answered correctly and change the number of correctly answered question
            Log.d(
                "Current",
                "Current Question $currentQuestion.correctAns and Selected Ans ${selectedOption.trim()}"
            )
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
                Log.d("Current", "User Selected ${selectedRB.text}")
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


//
//
//lateinit var btnSubmit: Button
//private lateinit var radioGroup: RadioGroup
//private lateinit var tvQuestion: TextView
//private lateinit var currentQuestion: QuestionModel
//
//var numOfCorrectlyAnsweredQues = 0;
//var totalNumberOfQuestions =
//    1;// pass here the length of the question list of which user opted the quiz
//
//var currIdx: Int = 0
//var isLastQuestionAnswered: Boolean = false
//var listOfAnswers =
//    mutableListOf<AnswerModel>() // because in result page i have to show what user answered
//var selectedOption = ""
//
//override fun onCreate(savedInstanceState: Bundle?) {
//    // code this in On Resume , it Will be better I guess
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_question_page)
//
//    radioGroup = findViewById<RadioGroup>(R.id.myRadioGroup)
//    btnSubmit = findViewById<Button>(R.id.btnSubmit)
//    btnSubmit.isEnabled = false
//    tvQuestion = findViewById<TextView>(R.id.tvQuestion)
//    tvQuestion.text = Constants.listOfMathQuestions[0].question
//    totalNumberOfQuestions = Constants.listOfMathQuestions.size
//
//    setupQnA(Constants.listOfMathQuestions[currIdx])// starts with i=0
//
//    btnSubmit.setOnClickListener {
//        if (currentQuestion.correctAns == selectedOption.trim()) {
//            Log.d("Current", "Inside if , user selected correct option")
//            numOfCorrectlyAnsweredQues += 1
//            listOfAnswers.add(
//                AnswerModel(
//                    currentQuestion,
//                    selectedOption,
//                    true
//                )
//            )
//        } else {
//            Log.d("Current", "Else, user selected wrong option")
//            listOfAnswers.add(
//                AnswerModel(
//                    currentQuestion,
//                    selectedOption,
//                    false
//                )
//            )
//        }
//
//        if (isLastQuestionAnswered) {
//            // go to next result page
//            Log.d("Current", "Number of correct answers : $numOfCorrectlyAnsweredQues")
//        } else {
//            currIdx += 1;
//            if (currIdx == (Constants.listOfMathQuestions.size - 1)) {// ie. last ele
//                btnSubmit.text = "Finish"
//            }
//            //check here if user answered correctly and change the number of correctly answered question
//            Log.d(
//                "Current",
//                "Current Question $currentQuestion.correctAns and Selected Ans ${selectedOption.trim()}"
//            )
//            //setupQNA for NextQuestion
//            radioGroup.clearCheck()// removes previously selected option
//            btnSubmit.isEnabled = false
//            setupQnA(Constants.listOfMathQuestions[currIdx])
//        }
//    }
//
//    setupRadioGroupListener()
//}
//
//private fun setupRadioGroupListener() {
//    radioGroup.setOnCheckedChangeListener { _, i ->
//        val selectedRB = findViewById<RadioButton>(i)
//        if (selectedRB != null) {
//            if (currIdx == (Constants.listOfMathQuestions.size - 1)) {
//                isLastQuestionAnswered = true
//            }
//            btnSubmit.isEnabled = true
//            selectedOption = selectedRB.text.toString()
//            Log.d("Current", "User Selected ${selectedRB.text}")
//        }
//    }
//}
//
//private fun setupQnA(Question: QuestionModel) {// setup radio button
//    currentQuestion = Question
//    val shuffledListOfOptions = Question.Options.shuffled()
//    tvQuestion.text = Question.question
//    for (i in 0 until radioGroup.childCount) {
//        (radioGroup.getChildAt(i) as RadioButton).text = shuffledListOfOptions[i]
//    }
//}