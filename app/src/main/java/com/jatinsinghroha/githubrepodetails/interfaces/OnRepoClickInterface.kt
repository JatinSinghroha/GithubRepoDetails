package com.jatinsinghroha.githubrepodetails.interfaces

import com.jatinsinghroha.githubrepodetails.models.reposmodel.Repo

interface OnRepoClickInterface {
    fun shareRepoDetails(details: String)

    fun goToRepoDetails(repo: Repo)
}