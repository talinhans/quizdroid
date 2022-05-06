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
                Log.d("Current", "User pressed Pref")
                startActivity(Intent(this, PreferenceActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    val FILE_NAME: String = "questions.json"
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
        // val list = repository.getListOfTopics()
        //  Log.d("QQQQQQ", "$list")
        // Log.d("QQQQQQ", "$listOfTopic")
        super.onResume()
    }

    private fun setUpRecyclerView() {
        layoutManager =
            LinearLayoutManager(baseContext) // this 2 is basically number of columns u want
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTopicList)
        recyclerView.layoutManager = layoutManager
        adapter = TopicListRecyclerView(this)
        recyclerView.adapter = adapter
    }

    override fun onTopicClick(position: Int) {
        Log.d("Current", "User clicked on topic $position")
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


//
//        val q1 = findViewById<TextView>(R.id.tvQ1)
//        q1.setOnClickListener {
//            var intent = Intent(this, TopicOverviewPage::class.java)
//            intent.putExtra(Constants.typeKey, Constants.mathVal)
//            startActivity(intent)
//        }
//        val q2 = findViewById<TextView>(R.id.tvQ2)
//        q2.setOnClickListener {
//            var intent = Intent(this, TopicOverviewPage::class.java)
//            intent.putExtra(Constants.typeKey, Constants.physicsVal)
//            startActivity(intent)
//        }
//        val q3 = findViewById<TextView>(R.id.tvQ3)
//        q3.setOnClickListener {
//            var intent = Intent(this, TopicOverviewPage::class.java)
//            intent.putExtra(Constants.typeKey, Constants.marvelSHVal)
//            startActivity(intent)
//        }
//        val q4 = findViewById<TextView>(R.id.tvQ4)
//        q4.setOnClickListener {
//            var intent = Intent(this, TopicOverviewPage::class.java)
//            intent.putExtra(Constants.typeKey, Constants.musicVal)
//            startActivity(intent)
//        }

//fun setListOfTopics(data: String): List<Topic> {
//    var jsonArray: JSONArray = JSONArray(data)
//    var listOfTopic = ArrayList<Topic>()
//    var topicMapper = TopicMapper()
//    var topicTitle = ""
//    var topicDesc = ""
//    var listOfQuestionEntity = ArrayList<QuestionEntity>()
//
//    for (i in 0 until jsonArray.length()) {
//        var jsonObject = jsonArray.getJSONObject(i)
//        topicTitle = jsonObject.getString("title")
//        topicDesc = jsonObject.getString("desc")
//        var questionBank = jsonObject.getString("questions")
//        var jsonArray1 = JSONArray(questionBank)
//        var questionEntity: QuestionEntity
//        for (i in 0 until jsonArray1.length()) {
//            var jsonObject = jsonArray1.getJSONObject(i)
//            var questionText = jsonObject.getString("text")
//            var correctAnswerIdxText = jsonObject.getString("answer")
//            var allOptionJson = jsonObject.getString("answers").toString()
//            var new1 = allOptionJson.removeSuffix("]")
//            var new2 = new1.removePrefix("[")
//            var toList = new2.split(",")
//            var allOptionsList = toList
//            questionEntity = QuestionEntity(questionText, correctAnswerIdxText, allOptionsList)
//            listOfQuestionEntity.add(questionEntity)
//            // Log.d("ZZZZZZZZzzzz", "all Options : ${allOptionsList[0]}")
//        }
//        var topicEntity = TopicEntity(topicTitle, topicDesc, listOfQuestionEntity)
//        var topic = topicMapper.mapFromEntity(topicEntity)
//        listOfTopic.add(topic)
//    }
//    Log.d("ZZZZZZZZZZ", "$listOfTopic")
//    return listOfTopic
//}