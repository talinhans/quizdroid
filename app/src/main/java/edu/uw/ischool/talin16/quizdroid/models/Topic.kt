package edu.uw.ischool.talin16.quizdroid.models

data class Topic(
    var title: String,
    var shortDescription: String,
    var longDescription: String,
    var questionBank: List<Quiz>
)