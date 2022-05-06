package edu.uw.ischool.talin16.quizdroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uw.ischool.talin16.quizdroid.Adapter.TopicListRecyclerView
import edu.uw.ischool.talin16.quizdroid.Pages.TopicOverviewPage
import edu.uw.ischool.talin16.quizdroid.others.Constants

class MainActivity : AppCompatActivity(), TopicListRecyclerView.OnTopicClickListener {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: TopicListRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        layoutManager =
            LinearLayoutManager(baseContext)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTopicList)
        recyclerView.layoutManager = layoutManager
        adapter = TopicListRecyclerView(this)
        recyclerView.adapter = adapter
    }

    override fun onTopicClick(position: Int) {
        var intent: Intent
        if (position == 0) {
            intent = Intent(this, TopicOverviewPage::class.java)
            intent.putExtra(Constants.typeKey, Constants.mathVal)
            startActivity(intent)
        } else if (position == 1) {
            intent = Intent(this, TopicOverviewPage::class.java)
            intent.putExtra(Constants.typeKey, Constants.physicsVal)
            startActivity(intent)
        } else if (position == 2) {
            intent = Intent(this, TopicOverviewPage::class.java)
            intent.putExtra(Constants.typeKey, Constants.marvelSHVal)
            startActivity(intent)
        } else {
            intent = Intent(this, TopicOverviewPage::class.java)
            intent.putExtra(Constants.typeKey, Constants.musicVal)
            startActivity(intent)
        }
    }
}
