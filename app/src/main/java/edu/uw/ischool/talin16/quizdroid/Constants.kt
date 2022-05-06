package edu.uw.ischool.talin16.quizdroid

object Constants {

    //bundleKeys
    val typeKey = "Type Selected"
    val mathVal = "Maths"
    val physicsVal = "Physics"
    val marvelSHVal = "Marvel"
    val musicVal = "Music"

    val mathsBrief = "This is Maths Brief"
    val physicsBrief = "This is Physics Brief"
    val marvelSHBrief = "This is Marvel Super Heroes Breif"
    val musicBrief = "This is music Breif"

    val listOfMathQuestions =
        listOf<QuestionModel>(
            QuestionModel("2+2", "4", listOf("4", "1", "2", "5")),
            QuestionModel("3+2", "5", listOf("6", "7", "8", "5")),
            QuestionModel("7*2", "14", listOf("14", "15", "12", "13")),
            QuestionModel("8*4", "32", listOf("32", "33", "34", "35"))
        )
    val listOfPhysicsQuestions =
        listOf<QuestionModel>(
            QuestionModel("what is formula of power", "VI", listOf("VI", "R", "MG", "W")),
            QuestionModel(
                "what is formula of kinetic energy",
                "1/2mv^2",
                listOf("1/2mv^2", "MG", "VI", "MGH")
            ),
        )
    val listOfMarvelSuperHeroesQuestions =
        listOf<QuestionModel>(
            QuestionModel(
                "Who is iron man",
                "Tony Stark",
                listOf("Tony Stark", "Bruce Banner", "Steve Rogers", "Thor")
            ),
            QuestionModel(
                "Who is hulk",
                "Bruce Banner",
                listOf("Bruce Banner", "Tony Stark", "Steve Rogers", "Thor")
            ),
            QuestionModel(
                "Who is captain america",
                "Steve Rogers",
                listOf("Steve Rogers", "Tony Stark", "Bruce Banner", "Thor")
            ),
        )
    val listOfMusicQuestions =
        listOf<QuestionModel>(
            QuestionModel("How many notes in an octave", "8", listOf("8", "3", "6", "7")),
            QuestionModel("How many sharp does B note has", "0", listOf("0", "1", "2", "3")),
        )


    var currQuestionBank = listOf<QuestionModel>()

    var lastUsersAnswer = ""
    lateinit var lastQuestion: QuestionModel

    var numOfCorrectlyAnsweredQues = 0;
    var totalNumberOfQuestions =
        0
    var currIdx: Int = 0
    var isLastQuestionAnswered: Boolean = false
    
    fun clearData() {
        currQuestionBank = listOf()
        lastUsersAnswer = ""
        lastQuestion = QuestionModel("", "", listOf(""))
        numOfCorrectlyAnsweredQues = 0
        totalNumberOfQuestions =
            0;
        currIdx = 0
        isLastQuestionAnswered = false

    }
}