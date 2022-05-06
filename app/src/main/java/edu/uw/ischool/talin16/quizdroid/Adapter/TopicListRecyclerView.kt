package edu.uw.ischool.talin16.quizdroid.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uw.ischool.talin16.quizdroid.QuizApp
import edu.uw.ischool.talin16.quizdroid.R
import edu.uw.ischool.talin16.quizdroid.models.Topic

class TopicListRecyclerView(var onTopicClickListener: OnTopicClickListener) :
    RecyclerView.Adapter<TopicListRecyclerView.ViewHolder>() {

    private var listOfTopics = QuizApp.getTopicRepositoryInstance().getListOfTopics()

    fun setAdapterData(list: List<Topic>) {
        listOfTopics = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvRVTitle)
        var tvShortDescription: TextView = itemView.findViewById(R.id.tvRVShortDescription)

        init {
            itemView.setOnClickListener {
                val pos = adapterPosition
                onTopicClickListener.onTopicClick(pos)
            }
        }
    }

    interface OnTopicClickListener {
        fun onTopicClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.topic_list_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = listOfTopics[position].title
        holder.tvShortDescription.text = listOfTopics[position].shortDescription
    }

    override fun getItemCount(): Int {
        return listOfTopics.size
    }
}