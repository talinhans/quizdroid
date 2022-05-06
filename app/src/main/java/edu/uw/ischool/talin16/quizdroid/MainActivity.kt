package edu.uw.ischool.talin16.quizdroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uw.ischool.talin16.quizdroid.Adapter.TopicListRecyclerView
import edu.uw.ischool.talin16.quizdroid.Pages.PreferenceActivity
import edu.uw.ischool.talin16.quizdroid.Pages.TopicOverviewPage
import edu.uw.ischool.talin16.quizdroid.others.Constants
import edu.uw.ischool.talin16.quizdroid.repository.TopicRepositoryImplementation

class MainActivity : AppCompatActivity(), TopicListRecyclerView.OnTopicClickListener {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: TopicListRecyclerView? = null


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.preferences -> {
                startActivity(Intent(this, PreferenceActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    lateinit var repository: TopicRepositoryImplementation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        adapter?.setAdapterData(QuizApp.getTopicRepositoryInstance().getListOfTopics())
    }

    override fun onResume() {
        repository = QuizApp.getTopicRepositoryInstance()
        var fileName = repository.FILE_NAME
        var res = repository.getJson(openFileInput(fileName))
        val listOfTopic = repository.convertFromJsonToListOfTopic(res)
        adapter?.setAdapterData(listOfTopic)
        repository.setListOfTopics(listOfTopic)
        super.onResume()
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
