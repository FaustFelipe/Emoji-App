package br.com.felipefaustini.data.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "login") val login: String? = null,
    @Json(name = "id") val id: Long? = null,
    @Json(name = "node_id") val nodeId: String? = null,
    @Json(name = "avatar_url") val avatarUrl: String? = null,
    @Json(name = "gravatar_url") val gravatarUrl: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "html_url") val htmlUrl: String? = null,
    @Json(name = "followers_url") val followersUrl: String? = null,
    @Json(name = "following_url") val followingUrl: String? = null,
    @Json(name = "gists_url") val gistsUrl: String? = null,
    @Json(name = "starred_url") val starredUrl: String? = null,
    @Json(name = "subscriptions_url") val subscriptionsUrl: String? = null,
    @Json(name = "organizations_url") val organizationsUrl: String? = null,
    @Json(name = "repos_url") val reposUrl: String? = null,
    @Json(name = "type") val type: String? = null,
    @Json(name = "site_admin") val siteAdmin: Boolean? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "company") val company: String? = null,
    @Json(name = "blog") val blog: String? = null,
    @Json(name = "location") val location: String? = null,
    @Json(name = "email") val email: String? = null,
    @Json(name = "bio") val bio: String? = null,
    @Json(name = "created_at") val createdAt: Date? = null,
    @Json(name = "updated_at") val updatedAt: Date? = null
)