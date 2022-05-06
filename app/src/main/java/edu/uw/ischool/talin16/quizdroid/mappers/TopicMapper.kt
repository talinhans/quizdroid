package edu.uw.ischool.talin16.quizdroid.mappers

import edu.uw.ischool.talin16.quizdroid.models.Quiz
import edu.uw.ischool.talin16.quizdroid.models.Topic
import edu.uw.ischool.talin16.quizdroid.models.TopicEntity

class TopicMapper : EntityMapper<TopicEntity, Topic> {
    override fun mapFromEntity(entity: TopicEntity): Topic {
        var quizMapper = QuizMapper()
        var listOfQuiz = ArrayList<Quiz>()
        for (i in 0 until entity.questions.size) {
            listOfQuiz.add(quizMapper.mapFromEntity(entity.questions[i]))
        }
        return Topic(entity.title, "", entity.desc, listOfQuiz)
    }

}