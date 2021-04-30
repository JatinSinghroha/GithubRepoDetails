package com.jatinsinghroha.githubrepodetails.interfaces

interface ValidateRepoInterface {
    fun validateRepo(repoOwner: String, repoName: String, actOnValidInValidInterface: ActOnValidInValidInterface)
}