package com.jatinsinghroha.githubrepodetails.models.reposmodel
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(primaryKeys= [ "id", "name" ] )
@Serializable
data class BranchItem(
        @NonNull
        var id: Long = 0,
        @Ignore
        @SerialName("commit")
        var commit: Commit? = null,
        var commitSha: String? = "",
        var commitURL: String? = "",
        @SerialName("name")
        @NonNull
        var name: String="",
        @SerialName("protected")
        var `protected`: Boolean?
){
    constructor() : this(0, null, "", "", "",false)
}

@Serializable
data class Commit(
        @SerialName("sha")
        var sha: String?,
        @SerialName("url")
        var url: String?
)

