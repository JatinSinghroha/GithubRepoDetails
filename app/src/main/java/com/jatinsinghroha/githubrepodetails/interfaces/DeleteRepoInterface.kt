package com.jatinsinghroha.githubrepodetails.interfaces

import com.jatinsinghroha.githubrepodetails.models.reposmodel.Repo

interface DeleteRepoInterface {
    fun deleteRepo(repo: Repo)
}