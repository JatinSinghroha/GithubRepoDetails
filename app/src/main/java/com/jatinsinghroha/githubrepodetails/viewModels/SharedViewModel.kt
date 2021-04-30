package com.jatinsinghroha.githubrepodetails.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jatinsinghroha.githubrepodetails.models.reposmodel.BranchItem
import com.jatinsinghroha.githubrepodetails.models.reposmodel.Repo

class SharedViewModel: ViewModel() {
    val listOfRepos: MutableLiveData<List<Repo>> = MutableLiveData<List<Repo>>()

    val listOfBranches: MutableLiveData<List<BranchItem>> = MutableLiveData<List<BranchItem>>()

}