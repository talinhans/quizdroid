package edu.uw.ischool.talin16.quizdroid

import android.app.Application
import android.util.Log
import edu.uw.ischool.talin16.quizdroid.repository.TopicRepositoryImplementation


class QuizApp : Application() { // Singleton Class
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

    val FILE_NAME: String = "questions.json"
    override fun onCreate() {
        Log.d("ZZZZ", "Inside OnCreate Of QuizApp Class")
        super.onCreate()
    }
}