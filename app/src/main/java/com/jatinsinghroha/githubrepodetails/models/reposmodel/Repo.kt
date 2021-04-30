package com.jatinsinghroha.githubrepodetails.models.reposmodel

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.*
import kotlinx.serialization.json.*
@Entity
@Serializable
data class Repo (
    @PrimaryKey
    var id: Long? = null,

    @SerialName("node_id")
    var nodeID: String? = null,

    var name: String? = null,

    @SerialName("full_name")
    var fullName: String? = null,

    var private: Boolean? = null,

    @SerialName("html_url")
    var htmlURL: String? = null,

    var description: String? = null,
    var fork: Boolean? = null,
    var url: String? = null,

    @SerialName("forks_url")
    var forksURL: String? = null,

    @SerialName("keys_url")
    var keysURL: String? = null,

    @SerialName("collaborators_url")
    var collaboratorsURL: String? = null,

    @SerialName("teams_url")
    var teamsURL: String? = null,

    @SerialName("hooks_url")
    var hooksURL: String? = null,

    @SerialName("issue_events_url")
    var issueEventsURL: String? = null,

    @SerialName("events_url")
    var eventsURL: String? = null,

    @SerialName("assignees_url")
    var assigneesURL: String? = null,

    @SerialName("branches_url")
    var branchesURL: String? = null,

    @SerialName("tags_url")
    var tagsURL: String? = null,

    @SerialName("blobs_url")
    var blobsURL: String? = null,

    @SerialName("git_tags_url")
    var gitTagsURL: String? = null,

    @SerialName("git_refs_url")
    var gitRefsURL: String? = null,

    @SerialName("trees_url")
    var treesURL: String? = null,

    @SerialName("statuses_url")
    var statusesURL: String? = null,

    @SerialName("languages_url")
    var languagesURL: String? = null,

    @SerialName("stargazers_url")
    var stargazersURL: String? = null,

    @SerialName("contributors_url")
    var contributorsURL: String? = null,

    @SerialName("subscribers_url")
    var subscribersURL: String? = null,

    @SerialName("subscription_url")
    var subscriptionURL: String? = null,

    @SerialName("commits_url")
    var commitsURL: String? = null,

    @SerialName("git_commits_url")
    var gitCommitsURL: String? = null,

    @SerialName("comments_url")
    var commentsURL: String? = null,

    @SerialName("issue_comment_url")
    var issueCommentURL: String? = null,

    @SerialName("contents_url")
    var contentsURL: String? = null,

    @SerialName("compare_url")
    var compareURL: String? = null,

    @SerialName("merges_url")
    var mergesURL: String? = null,

    @SerialName("archive_url")
    var archiveURL: String? = null,

    @SerialName("downloads_url")
    var downloadsURL: String? = null,

    @SerialName("issues_url")
    var issuesURL: String? = null,

    @SerialName("pulls_url")
    var pullsURL: String? = null,

    @SerialName("milestones_url")
    var milestonesURL: String? = null,

    @SerialName("notifications_url")
    var notificationsURL: String? = null,

    @SerialName("labels_url")
    var labelsURL: String? = null,

    @SerialName("releases_url")
    var releasesURL: String? = null,

    @SerialName("deployments_url")
    var deploymentsURL: String? = null,

    @SerialName("created_at")
    var createdAt: String? = null,

    @SerialName("updated_at")
    var updatedAt: String? = null,

    @SerialName("pushed_at")
    var pushedAt: String? = null,

    @SerialName("git_url")
    var gitURL: String? = null,

    @SerialName("ssh_url")
    var sshURL: String? = null,

    @SerialName("clone_url")
    var cloneURL: String? = null,

    @SerialName("svn_url")
    var svnURL: String? = null,

    var size: Long? = null,

    @SerialName("stargazers_count")
    var stargazersCount: Long? = null,

    @SerialName("watchers_count")
    var watchersCount: Long? = null,

    @SerialName("has_issues")
    var hasIssues: Boolean? = null,

    @SerialName("has_projects")
    var hasProjects: Boolean? = null,

    @SerialName("has_downloads")
    var hasDownloads: Boolean? = null,

    @SerialName("has_wiki")
    var hasWiki: Boolean? = null,

    @SerialName("has_pages")
    var hasPages: Boolean? = null,

    @SerialName("forks_count")
    var forksCount: Long? = null,

    var archived: Boolean? = null,
    var disabled: Boolean? = null,

    @SerialName("open_issues_count")
    var openIssuesCount: Long? = null,

    var forks: Long? = null,

    @SerialName("open_issues")
    var openIssues: Long? = null,

    var watchers: Long? = null,

    @SerialName("default_branch")
    var defaultBranch: String? = null,


    @SerialName("network_count")
    var networkCount: Long? = null,

    @SerialName("subscribers_count")
    var subscribersCount: Long? = null
)

@Entity
@Serializable
data class Organization (
        var login: String? = null,
        @PrimaryKey
        var id: Long? = null,

        @SerialName("node_id")
        var nodeID: String? = null,

        @SerialName("avatar_url")
        var avatarURL: String? = null,

        @SerialName("gravatar_id")
        var gravatarID: String? = null,

        var url: String? = null,

        @SerialName("html_url")
        var htmlURL: String? = null,

        @SerialName("followers_url")
        var followersURL: String? = null,

        @SerialName("following_url")
        var followingURL: String? = null,

        @SerialName("gists_url")
        var gistsURL: String? = null,

        @SerialName("starred_url")
        var starredURL: String? = null,

        @SerialName("subscriptions_url")
        var subscriptionsURL: String? = null,

        @SerialName("organizations_url")
        var organizationsURL: String? = null,

        @SerialName("repos_url")
        var reposURL: String? = null,

        @SerialName("events_url")
        var eventsURL: String? = null,

        @SerialName("received_events_url")
        var receivedEventsURL: String? = null,

        var type: String? = null,

        @SerialName("site_admin")
        var siteAdmin: Boolean? = null
)

@Entity
@Serializable
data class License (
        var key: String? = null,
        @PrimaryKey
        var name: String? = null,

        @SerialName("spdx_id")
        var spdxID: String? = null,

        var url: String? = null,

        @SerialName("node_id")
        var nodeID: String? = null
)





