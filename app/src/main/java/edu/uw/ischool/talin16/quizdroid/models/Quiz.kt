package edu.uw.ischool.talin16.quizdroid.models

data class Quiz(
    var question: String,
    var op1: String,
    var op2: String,
    var op3: String,
    var op4: String,
    var correctOption: Int
)