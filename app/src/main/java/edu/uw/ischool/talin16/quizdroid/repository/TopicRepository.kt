package edu.uw.ischool.talin16.quizdroid.repository

import edu.uw.ischool.talin16.quizdroid.models.Topic

interface TopicRepository {
    fun getListOfTopics(): List<Topic>
}