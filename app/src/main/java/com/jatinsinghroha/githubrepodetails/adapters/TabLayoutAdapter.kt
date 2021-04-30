package com.jatinsinghroha.githubrepodetails.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jatinsinghroha.githubrepodetails.fragments.BranchesFragment
import com.jatinsinghroha.githubrepodetails.fragments.IssuesFragment
import com.jatinsinghroha.githubrepodetails.models.reposmodel.IssueElement
import com.jatinsinghroha.githubrepodetails.models.reposmodel.Repo

class TabLayoutAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, val repo: Repo, val issues: List<IssueElement>): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                BranchesFragment.newInstance(repo)
            }
            1->{
                IssuesFragment.newInstance(issues)
            }
            else-> BranchesFragment.newInstance(repo)
        }
    }

}