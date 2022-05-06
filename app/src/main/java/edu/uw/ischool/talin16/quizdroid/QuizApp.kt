package edu.uw.ischool.talin16.quizdroid

import android.app.Application
import android.util.Log
import edu.uw.ischool.talin16.quizdroid.repository.TopicRepositoryImplementation


class QuizApp : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: QuizApp
        var myRepoInstance: TopicRepositoryImplementation? = null
        fun getTopicRepositoryInstance(): TopicRepositoryImplementation {
            if (myRepoInstance == null) {
                myRepoInstance = TopicRepositoryImplementation()
            }
            return myRepoInstance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}