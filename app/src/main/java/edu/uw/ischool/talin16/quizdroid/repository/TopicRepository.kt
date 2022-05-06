package edu.uw.ischool.talin16.quizdroid.repository

import edu.uw.ischool.talin16.quizdroid.models.Topic

interface TopicRepository {
    fun getListOfTopics(): List<Topic>
    fun setListOfTopics(list: List<Topic>): Unit
    fun convertFromJsonToListOfTopic(data: String): List<Topic>
    fun getDataFromNetwork(url:String)
}