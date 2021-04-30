package com.jatinsinghroha.githubrepodetails.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jatinsinghroha.githubrepodetails.Extensions.inflate
import com.jatinsinghroha.githubrepodetails.R
import com.jatinsinghroha.githubrepodetails.models.reposmodel.CommitItem
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CommitsAdapter(val context: Context, private val commitItems: List<CommitItem>) : RecyclerView.Adapter<CommitsAdapter.ViewHolder>(){
    var dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.rv_commit_item, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commitItem = commitItems[position]
        var date: Date =dateFormat.parse(commitItem.commit?.committer?.date.toString().substring(0,10))
        var formatter: DateFormat = SimpleDateFormat("d MMM yy")
        var dateStr: String = formatter.format(date)
        holder.commitDateTV.text = dateStr
        Glide.with(context).load(commitItem.author?.avatarURL).centerCrop().placeholder(R.drawable.ic_baseline_arrow_circle_down_24).into(holder.commitAvatar)
        holder.commitShaTV.text = commitItem.sha
        holder.commitMessageTV.text = commitItem.commit?.message
        holder.committerName.text = commitItem.commit?.committer?.name
    }

    override fun getItemCount(): Int {
        return commitItems.size
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val commitDateTV:TextView = v.findViewById(R.id.commitDateTV)
        val commitShaTV : TextView = v.findViewById(R.id.commitShaTV)
        val commitAvatar: ImageView = v.findViewById(R.id.commitAvatar)
        val commitMessageTV: TextView = v.findViewById(R.id.commitMessageTV)
        val committerName: TextView = v.findViewById(R.id.committerName)
    }
}