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
import com.jatinsinghroha.githubrepodetails.models.reposmodel.IssueElement

class IssuesAdapter(val context: Context, private val listOfIssues: List<IssueElement>) : RecyclerView.Adapter<IssuesAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesAdapter.ViewHolder {
        return IssuesAdapter.ViewHolder(parent.inflate(R.layout.rv_issue_item, false))
    }

    override fun onBindViewHolder(holder: IssuesAdapter.ViewHolder, position: Int) {
        val issue = listOfIssues[position]
        holder.issueDescTV.text = issue.title
        holder.creatorNameTV.text = issue.userName
        Glide.with(context).load(issue.userAvatarURL).centerCrop().placeholder(R.drawable.ic_baseline_arrow_circle_down_24).into(holder.creatorAvatarIV)
    }

    override fun getItemCount(): Int {
        return listOfIssues.size
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val issueDescTV: TextView = v.findViewById(R.id.issue_item_desc)
        val creatorNameTV: TextView = v.findViewById(R.id.creatorName)
        val creatorAvatarIV: ImageView = v.findViewById(R.id.avatarImageView)
    }
}