package br.com.felipefaustini.data.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReposResponse(
    @Json(name = "id") val id: Long? = null,
    @Json(name = "node_id") val nodeId: String? = null,
    @Json(name = "html_url") val htmlUrl: String? = null
)