package com.example.marvelcompose.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "data") val data: Data
)

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "results") val results: Results
)

@JsonClass(generateAdapter = true)
data class Results(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail
)

@JsonClass(generateAdapter = true)
data class Thumbnail(
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
)