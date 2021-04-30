package com.jatinsinghroha.githubrepodetails.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jatinsinghroha.githubrepodetails.Extensions.inflate
import com.jatinsinghroha.githubrepodetails.R
import com.jatinsinghroha.githubrepodetails.interfaces.OnBranchClick
import com.jatinsinghroha.githubrepodetails.models.reposmodel.BranchItem

class BranchesAdapter(private val listOfBranches: List<BranchItem>, val onBranchClick: OnBranchClick):RecyclerView.Adapter<BranchesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchesAdapter.ViewHolder {
        return BranchesAdapter.ViewHolder(parent.inflate(R.layout.rv_branch_item, false))
    }

    override fun onBindViewHolder(holder: BranchesAdapter.ViewHolder, position: Int) {
        holder.branchNameTV.text = listOfBranches[position].name
        holder.branchNameTV.setOnClickListener{
            onBranchClick.onClickBranch(listOfBranches[position].name, listOfBranches[position].commitSha!!)
        }
    }

    override fun getItemCount(): Int {
        return listOfBranches.size
    }

    class ViewHolder(v: View):RecyclerView.ViewHolder(v) {
        val branchNameTV: TextView = v.findViewById(R.id.nameBranchTV)
    }

}