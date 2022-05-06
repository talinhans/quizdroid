package edu.uw.ischool.talin16.quizdroid.Pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.talin16.quizdroid.MyService
import edu.uw.ischool.talin16.quizdroid.QuizApp
import edu.uw.ischool.talin16.quizdroid.R


class PreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)
        val etURL = findViewById<EditText>(R.id.etPreferenceUrl)
        val etTime = findViewById<EditText>(R.id.etPreferenceTime)
        val btnSet = findViewById<Button>(R.id.btnPrefSet)
        val myService = Intent(applicationContext, MyService::class.java)
        stopService(myService)
        btnSet.setOnClickListener {
            QuizApp.getTopicRepositoryInstance().setUrl(etURL.text.toString())
            QuizApp.getTopicRepositoryInstance()
                .setHitTimeInterval(etTime.text.toString().trim().toInt())
            startService(myService)
            finish()
        }
    }
}