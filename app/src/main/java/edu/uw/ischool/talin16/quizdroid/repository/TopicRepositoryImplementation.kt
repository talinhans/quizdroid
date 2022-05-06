package edu.uw.ischool.talin16.quizdroid.repository

import android.app.DownloadManager
import android.net.Uri
import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.uw.ischool.talin16.quizdroid.mappers.TopicMapper
import edu.uw.ischool.talin16.quizdroid.models.Topic
import edu.uw.ischool.talin16.quizdroid.models.TopicEntity
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class TopicRepositoryImplementation : TopicRepository {
    override fun getListOfTopics(): List<Topic> {
        return myListOfTopics
    }

    override fun setListOfTopics(list: List<Topic>) {
        myListOfTopics = list
    }

    private var URL = "https://tednewardsandbox.site44.com/questions.json"
    private var hitTimeInterval: Int = 60000 // initially i'll hit it every 1 min
    fun setHitTimeInterval(time: Int) {
        hitTimeInterval = time * 1000 * 60
    }
    fun getHitTimeInterval(): Int {
        return hitTimeInterval
    }
    fun setUrl(url: String) {
        URL = url.trim()
    }

    fun getUrl(): String {
        return URL
    }

    private var myListOfTopics: List<Topic> = listOf()

    var data: String = ""

    var livedata: MutableLiveData<String> = MutableLiveData()

    lateinit var handler: Handler
    var isSuccess: MutableLiveData<Boolean> = MutableLiveData(false)
    fun doCallToGetJsonDataFromNetwork(myHandler: Handler) {
        handler = myHandler
        myThread().start()
    }

    fun getLiveData(): MutableLiveData<String> {
        return livedata
    }

    inner class myThread : Thread() {
        override fun run() {
            try {
                var url = URL(getUrl())
                var httpUrlConnection = url.openConnection() as HttpURLConnection
                var inputStream = httpUrlConnection.inputStream
                var br = BufferedReader(InputStreamReader(inputStream))
                var sb: StringBuilder = StringBuilder()
                var line = br.readLine()
                var text: String
                while (line != null) {
                    text = line
                    sb.append(text).append("\n");
                    line = br.readLine()
                }
                data = sb.toString()
            } catch (e: MalformedURLException) {
                data = ""
                Log.d("Exception Caught", "caught in exception $e")
            } catch (e: IOException) {
                data = ""
                Log.d("Exception Caught", "caught in exception $e")
            } catch (e: JSONException) {
                data = ""
                Log.d("Exception Caught", "caught in exception $e")
            } catch (e: Exception) {
                data = ""
                Log.d("Exception Caught", "caught in exception $e")
            } finally {
                handler.post(Runnable {
                    if (data.isNullOrEmpty()) {
                        isSuccess.value = false
                    } else {
                        isSuccess.value = true
                    }
                    livedata.value = data
                })
            }

            super.run()
        }
    }

    override fun convertFromJsonToListOfTopic(data: String): List<Topic> {
        var listOfTopic = ArrayList<Topic>()
        var topicMapper = TopicMapper()
        var list = convertFromJsonToListOfTopicEntity(data)
        for (i in list.indices) {
            listOfTopic.add(topicMapper.mapFromEntity(list[i]))
        }
        return listOfTopic
    }

    override fun getDataFromNetwork(url: String) {
        var request = DownloadManager.Request(Uri.parse(url))
    }

    private fun convertFromJsonToListOfTopicEntity(json: String?): List<TopicEntity> {
        var gson: Gson = Gson()
        val type = object : TypeToken<List<TopicEntity>>() {}.type
        return gson.fromJson(json, type);
    }

}