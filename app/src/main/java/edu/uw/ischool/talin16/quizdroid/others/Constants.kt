package edu.uw.ischool.talin16.quizdroid.others

import edu.uw.ischool.talin16.quizdroid.models.Quiz

object Constants {

    //bundleKeys
    val typeKey = "Type Selected"
    val mathVal = "Maths"
    val physicsVal = "Physics"
    val marvelSHVal = "Marvel"
    val musicVal = "Music"

    var currQuestionBank = listOf<Quiz>()

    var lastUsersAnswer = ""
    lateinit var lastQuestion: Quiz

    var numOfCorrectlyAnsweredQues = 0;
    var totalNumberOfQuestions =
        0
    var currIdx: Int = 0
    var isLastQuestionAnswered: Boolean = false


    fun clearData() {
        currQuestionBank = listOf()
        lastUsersAnswer = ""
        lastQuestion = Quiz("", "", "", "", "", 0)
        numOfCorrectlyAnsweredQues = 0;
        totalNumberOfQuestions =
            0
        currIdx = 0
        isLastQuestionAnswered = false

    }

}