package com.jatinsinghroha.githubrepodetails.models.reposmodel

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Entity
@Serializable
data class IssueElement (
        var url: String? = null,

        @SerialName("repository_url")
        var repositoryURL: String? = null,

        @PrimaryKey
        @SerialName("html_url")
        var htmlURL: String? = null,

        var id: Long? = null,

        @SerialName("node_id")
        var nodeID: String? = null,

        var number: Long? = null,
        var title: String? = null,
        @Ignore
        var user: User? = null,

        @NonNull
        var userAvatarURL: String = "",
        @NonNull
        var userName: String = "",
){
    constructor():this("", "", "", 0, "", 0,"",null, "","")
}

@Serializable
data class User (
        var login: String? = null,
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

        @SerialName("organizations_url")
        var organizationsURL: String? = null,

        @SerialName("repos_url")
        var reposURL: String? = null,

        @SerialName("events_url")
        var eventsURL: String? = null,


        var type: Type? = null,
)

@Serializable
enum class Type(val varue: String) {
    User("User");

    companion object : KSerializer<Type> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.Type", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): Type = when (val varue = decoder.decodeString()) {
            "User" -> User
            else   -> throw IllegalArgumentException("Type could not parse: $varue")
        }
        override fun serialize(encoder: Encoder, varue: Type) {
            return encoder.encodeString(varue.varue)
        }
    }
}



