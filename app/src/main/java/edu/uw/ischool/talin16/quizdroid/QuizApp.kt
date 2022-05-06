package edu.uw.ischool.talin16.quizdroid

import android.app.Application
import android.nfc.Tag
import android.util.Log
import edu.uw.ischool.talin16.quizdroid.repository.TopicRepositoryImplementation

class QuizApp : Application() { // Singleton Class
    init {
        instance = this
    }
    companion object {
        lateinit var instance: QuizApp
        fun getTopicRepositoryInstance() = TopicRepositoryImplementation()
    }
    override fun onCreate() {
        super.onCreate()
        Log.d("Application Class" , "Inside OnCreate Of QuizApp Class")
    }
}