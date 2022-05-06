package edu.uw.ischool.talin16.quizdroid.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuestionEntity(
    @SerializedName("text")
    var text: String,// this is the question
    @SerializedName("answer")
    var answer: String,
    @SerializedName("answers")
    var answers: List<String>
) : Parcelable