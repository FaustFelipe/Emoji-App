package br.com.felipefaustini.domain.models

import java.util.*

data class User(
    var login: String = "",
    var id: Long = -1,
    var nodeId: String = "",
    var avatarUrl: String = "",
    var gravatarUrl: String = "",
    var url: String = "",
    var htmlUrl: String = "",
    var followersUrl: String = "",
    var followingUrl: String = "",
    var gistsUrl: String = "",
    var starredUrl: String = "",
    var subscriptionsUrl: String = "",
    var organizationsUrl: String = "",
    var reposUrl: String = "",
    var type: String = "",
    var siteAdmin: Boolean = false,
    var name: String = "",
    var company: String = "",
    var blog: String = "",
    var location: String = "",
    var email: String = "",
    var bio: String = "",
    var createdAt: Date? = null,
    var updatedAt: Date? = null
)