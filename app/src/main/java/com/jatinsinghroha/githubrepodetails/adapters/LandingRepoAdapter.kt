package com.jatinsinghroha.githubrepodetails.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jatinsinghroha.githubrepodetails.Extensions.inflate
import com.jatinsinghroha.githubrepodetails.R
import com.jatinsinghroha.githubrepodetails.interfaces.OnRepoClickInterface
import com.jatinsinghroha.githubrepodetails.models.reposmodel.Repo

class LandingRepoAdapter(private val listOfRepos: List<Repo>, private val onRepoClickInterface: OnRepoClickInterface):  RecyclerView.Adapter<LandingRepoAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.landing_repo_item, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = listOfRepos[position]
        holder.landRepoName.text = repo.name
        holder.landRepoDesc.text = repo.description
        holder.repoShareBtn.setOnClickListener{
            onRepoClickInterface.shareRepoDetails("Repository Name: ${repo.name}\n"
            +"Description: ${repo.description}\nURL: ${repo.htmlURL}")
        }


        holder.linearLayoutNameDesc.setOnClickListener{onRepoClickInterface.goToRepoDetails(repo)}
    }

    override fun getItemCount(): Int {
        return listOfRepos.size
    }

    class ViewHolder (v: View): RecyclerView.ViewHolder(v) {
        val landRepoName: TextView = v.findViewById(R.id.land_repo_name)
        val landRepoDesc: TextView = v.findViewById(R.id.land_repo_desc)
        val repoShareBtn: ImageView = v.findViewById(R.id.repoShareBtn)
        val linearLayoutNameDesc: LinearLayout = v.findViewById(R.id.linearLayoutNameDesc)
    }
}