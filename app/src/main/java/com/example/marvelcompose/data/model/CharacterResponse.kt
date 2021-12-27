package com.example.marvelcompose.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "data") val data: Data
)

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "offset") val offset: Int,
    @Json(name = "limit") val limit: Int,
    @Json(name = "results") val results: List<Star>
)

@JsonClass(generateAdapter = true)
data class Star(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail
) {
    fun getPathPicture() = URLDecoder.decode(thumbnail.path, StandardCharsets.UTF_8.toString()) + "." + thumbnail.extension

    init {
        thumbnail.path = URLEncoder.encode(thumbnail.path, StandardCharsets.UTF_8.toString())
    }
}

@JsonClass(generateAdapter = true)
data class Thumbnail(
    @Json(name = "path") var path: String,
    @Json(name = "extension") val extension: String
)