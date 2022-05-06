package edu.uw.ischool.talin16.quizdroid.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.uw.ischool.talin16.quizdroid.mappers.TopicMapper
import edu.uw.ischool.talin16.quizdroid.models.Topic
import edu.uw.ischool.talin16.quizdroid.models.TopicEntity
import java.io.*

class TopicRepositoryImplementation : TopicRepository {
    override fun getListOfTopics(): List<Topic> {
        return myListOfTopics
    }

    override fun setListOfTopics(list: List<Topic>) {
        myListOfTopics = list
    }

    private var myListOfTopics: List<Topic> = listOf()
    val FILE_NAME: String = "questions.json"

    fun getJson(openFileInput: FileInputStream?): String {
        var result = ""
        var fis: FileInputStream? = null
        try {
            fis = openFileInput
            var isr: InputStreamReader = InputStreamReader(fis)
            var br: BufferedReader = BufferedReader(isr)
            var sb: StringBuilder = StringBuilder()
            var text: String
            var line = br.readLine()
            while (line != null) {
                text = line
                sb.append(text).append("\n");
                line = br.readLine()
            }
            result = sb.toString()
        } catch (e: FileNotFoundException) {
            Log.d("ZZZZZ", e.message.toString())
        } catch (e: IOException) {
            Log.d("ZZZZZ", e.message.toString())
        } catch (e: Exception) {
            Log.d("ZZZZZ", e.message.toString())
        } finally {
            if (fis != null) {
                try {
                    fis.close()
                } catch (e: IOException) {
                    Log.d("ZZZZZ", e.message.toString())
                }
            }
        }
        return result
    }

    override fun convertFromJsonToListOfTopic(data: String): List<Topic> {
        var listOfTopic = ArrayList<Topic>()
        var topicMapper = TopicMapper()
        var list = convertFromJsonToListOfTopicEntity(data)
        for (i in list.indices) {
            listOfTopic.add(topicMapper.mapFromEntity(list[i]))
        }
        //Log.d("XXXXX", "${list[0]}")
        Log.d("WWWWWWWW", "Topic ${listOfTopic[1]}")
        return listOfTopic
    }

    private fun convertFromJsonToListOfTopicEntity(json: String?): List<TopicEntity> {
        var gson: Gson = Gson()
        val type = object : TypeToken<List<TopicEntity>>() {}.type
        return gson.fromJson(json, type);
    }

}