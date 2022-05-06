package edu.uw.ischool.talin16.quizdroid

import android.app.Service
import android.content.Intent
import android.os.Handler

import android.os.IBinder
import android.util.Log
import java.util.*


class MyService() : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    var t: Timer = Timer()
    override fun onCreate() {
        super.onCreate()
        startService(Intent(this, MyService::class.java))

        var repository = QuizApp.getTopicRepositoryInstance()
        var mHandler = repository.handler


        val handler = Handler()
        val doAsynchronousTask: TimerTask = object : TimerTask() {
            override fun run() {
                handler.post(Runnable {
                    repository.doCallToGetJsonDataFromNetwork(mHandler)
                })
            }
        }
        t.schedule(doAsynchronousTask, 0, getHitTime())
    }

    companion object {
        fun getHitTime(): Long {
            return QuizApp.getTopicRepositoryInstance().getHitTimeInterval().toLong()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        t.cancel()
        super.onDestroy()
    }

}