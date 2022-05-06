package edu.uw.ischool.talin16.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val q1 = findViewById<TextView>(R.id.tvQ1)
        q1.setOnClickListener {
            var intent = Intent(this, TopicOverviewPage::class.java)
            intent.putExtra(Constants.typeKey, Constants.mathVal)
            startActivity(intent)
        }
        val q2 = findViewById<TextView>(R.id.tvQ2)
        q2.setOnClickListener {
            var intent = Intent(this, TopicOverviewPage::class.java)
            intent.putExtra(Constants.typeKey, Constants.physicsVal)
            startActivity(intent)
        }
        val q3 = findViewById<TextView>(R.id.tvQ3)
        q3.setOnClickListener {
            var intent = Intent(this, TopicOverviewPage::class.java)
            intent.putExtra(Constants.typeKey, Constants.marvelSHVal)
            startActivity(intent)
        }
        val q4 = findViewById<TextView>(R.id.tvQ4)
        q4.setOnClickListener {
            var intent = Intent(this, TopicOverviewPage::class.java)
            intent.putExtra(Constants.typeKey, Constants.musicVal)
            startActivity(intent)
        }
    }
}