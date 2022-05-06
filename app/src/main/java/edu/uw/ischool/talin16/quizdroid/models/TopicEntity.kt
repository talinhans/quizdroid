package edu.uw.ischool.talin16.quizdroid.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopicEntity(
    @SerializedName("title")
    var title: String,
    @SerializedName("desc")
    var desc: String,
    @SerializedName("questions")
    var questions: List<QuestionEntity>
) : Parcelable