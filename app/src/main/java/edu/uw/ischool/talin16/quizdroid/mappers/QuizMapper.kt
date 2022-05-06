package edu.uw.ischool.talin16.quizdroid.mappers

import edu.uw.ischool.talin16.quizdroid.models.QuestionEntity
import edu.uw.ischool.talin16.quizdroid.models.Quiz

class QuizMapper : EntityMapper<QuestionEntity, Quiz> {

    override fun mapFromEntity(entity: QuestionEntity): Quiz {
        return Quiz(
            entity.text,
            entity.answers[0],
            entity.answers[1],
            entity.answers[2],
            entity.answers[3],
            entity.answer.trim().toInt()
        )
    }

}