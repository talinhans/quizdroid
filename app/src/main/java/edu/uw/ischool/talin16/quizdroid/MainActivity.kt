package edu.uw.ischool.talin16.quizdroid

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

    lateinit var repository: TopicRepositoryImplementation
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        QuizApp.getTopicRepositoryInstance().handler = Handler()

        val intent = Intent(this, MyService::class.java)
        startService(intent)

        setUpRecyclerView()
        progressBar = findViewById<ProgressBar>(R.id.progressBarMainActivity)
        progressBar.visibility = View.VISIBLE
        adapter?.setAdapterData(QuizApp.getTopicRepositoryInstance().getListOfTopics())
        repository = QuizApp.getTopicRepositoryInstance()
       // repository.doCallToGetJsonDataFromNetwork(Handler())
    }

    private fun checkAirPlaneMode(): Boolean {
        var isOn = false
        if (Settings.Global.getInt(
                this.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON,
                0
            ) != 0
        ) {
            isOn = true;
            Log.d("GGGGG", "Airplane Mode is On")
            MaterialAlertDialogBuilder(this)
                .setTitle("Airplane Mode is ON")
                .setMessage("Do u want to switch is off?")
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }.setPositiveButton("Yes") { dialog, _ ->
                    startActivityForResult(
                        Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS),
                        0
                    );
                }.show()
        } else {
            Log.d("GGGGG", "Airplane Mode is Off")
        }
        return isOn
    }

    private fun isInternetAvailable(): Boolean {
        var isConnected = false
        try {
            var manager: ConnectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var networkInfo = manager.activeNetworkInfo
            isConnected =
                (networkInfo != null && networkInfo.isConnected)

        } catch (e: Exception) {
            Log.d("YYYYYY", "CHecking Connection is connected = ${isConnected}")
        }
        Log.d("YYYYYY", "CHecking Connection is connected = ${isConnected}")
        return isConnected
    }

    override fun onResume() {
        if (!checkAirPlaneMode()) {
            if (!isInternetAvailable()) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("You are offline")
                    .setMessage("No access to the internet")
                    .setNegativeButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }.show()
            }
        }
        repository.getLiveData().observe(this, Observer { data ->

            progressBar.visibility = View.GONE
            repository.isSuccess.observe(this, Observer { response ->
                if (response) {
                    Log.d("OOOOO", "Inside MainActivity $data")
                    if (!data.isEmpty()) {
                        val listOfTopic = repository.convertFromJsonToListOfTopic(data)
                        adapter?.setAdapterData(listOfTopic)
                        repository.setListOfTopics(listOfTopic)
                    }
                } else {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Download Failed")
                        .setMessage("Do you want to try Again?")
                        .setNeutralButton("Close") { dialogue, _ -> dialogue.dismiss() }
                        .setNegativeButton("Try Again Later , Exit App") { dialog, _ ->
                            dialog.dismiss()
                            finishAndRemoveTask();
                        }.setPositiveButton("Retry") { dialog, _ ->
                            repository.doCallToGetJsonDataFromNetwork(Handler())
                            dialog.dismiss()
                        }.show()
                }
            })
        })

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
