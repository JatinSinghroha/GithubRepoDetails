package com.jatinsinghroha.githubrepodetails.models.reposmodel

import kotlinx.serialization.*

@Serializable
data class CommitItem (
    val sha: String? = null,

    @SerialName("node_id")
    val nodeID: String? = null,

    val commit: CommitClass? = null,
    val url: String? = null,

    @SerialName("html_url")
    val htmlURL: String? = null,

    @SerialName("comments_url")
    val commentsURL: String? = null,

    val author: CommitAuthor? = null,
    val committer: CommitAuthor? = null,
)

@Serializable
data class CommitAuthor (
    val login: String? = null,
    val id: Long? = null,

    @SerialName("node_id")
    val nodeID: String? = null,

    @SerialName("avatar_url")
    val avatarURL: String? = null,

    @SerialName("gravatar_id")
    val gravatarID: String? = null,

    val url: String? = null,

    @SerialName("html_url")
    val htmlURL: String? = null,

    @SerialName("followers_url")
    val followersURL: String? = null,

    @SerialName("following_url")
    val followingURL: String? = null,

    @SerialName("gists_url")
    val gistsURL: String? = null,

    @SerialName("starred_url")
    val starredURL: String? = null,

    @SerialName("subscriptions_url")
    val subscriptionsURL: String? = null,

    @SerialName("organizations_url")
    val organizationsURL: String? = null,

    @SerialName("repos_url")
    val reposURL: String? = null,

    @SerialName("events_url")
    val eventsURL: String? = null,

    @SerialName("received_events_url")
    val receivedEventsURL: String? = null,

    val type: Type? = null,

    @SerialName("site_admin")
    val siteAdmin: Boolean? = null
)


@Serializable
data class CommitClass (
    val author: CommitAuthorClass? = null,
    val committer: CommitAuthorClass? = null,
    val message: String? = null,
    val url: String? = null,

    @SerialName("comment_count")
    val commentCount: Long? = null,

)

@Serializable
data class CommitAuthorClass (
    val name: String? = null,
    val email: String? = null,
    val date: String? = null
)
