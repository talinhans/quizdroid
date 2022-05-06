package edu.uw.ischool.talin16.quizdroid.others

import edu.uw.ischool.talin16.quizdroid.models.AnswerModel
import edu.uw.ischool.talin16.quizdroid.models.QuestionModel
import edu.uw.ischool.talin16.quizdroid.models.Quiz

object Constants {

    //bundleKeys
    const val typeKey = "Type Selected"
    const val mathVal = "Maths"
    const val physicsVal = "Physics"
    const val marvelSHVal = "Marvel"
    const val musicVal = "Music"

    var currQuestionBank = listOf<Quiz>()

    var lastUsersAnswer = ""
    lateinit var lastQuestion: Quiz

    var numOfCorrectlyAnsweredQues = 0;
    var totalNumberOfQuestions =
        0;// pass here the length of the question list of which user opted the quiz
    var currIdx: Int = 0
    var isLastQuestionAnswered: Boolean = false
    var listOfAnswers =
        mutableListOf<AnswerModel>() // because in result page i have to show what user answered


    fun clearData() {
        currQuestionBank = listOf()
        lastUsersAnswer = ""
        lastQuestion = Quiz("", "", "", "", "", 0)
        numOfCorrectlyAnsweredQues = 0;
        totalNumberOfQuestions =
            0;// pass here the length of the question list of which user opted the quiz
        currIdx = 0
        isLastQuestionAnswered = false
        listOfAnswers =
            mutableListOf<AnswerModel>() // because in result page i have to show what user answered

    }

}