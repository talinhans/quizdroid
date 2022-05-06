package edu.uw.ischool.talin16.quizdroid

data class AnswerModel(
    var question: QuestionModel,
    var answeredByUser: String,
    var isCorrect: Boolean
)