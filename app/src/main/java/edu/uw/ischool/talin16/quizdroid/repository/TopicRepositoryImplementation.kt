package edu.uw.ischool.talin16.quizdroid.repository

import edu.uw.ischool.talin16.quizdroid.DummyData
import edu.uw.ischool.talin16.quizdroid.models.Topic

class TopicRepositoryImplementation : TopicRepository {
    override fun getListOfTopics(): List<Topic> {
        return listOf(DummyData.topic1, DummyData.topic2, DummyData.topic3, DummyData.topic4)
    }
}