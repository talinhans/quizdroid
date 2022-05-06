package edu.uw.ischool.talin16.quizdroid.models

data class QuestionModel(
    var question: String,
    var correctAns: String,
    var Options:List<String>
)