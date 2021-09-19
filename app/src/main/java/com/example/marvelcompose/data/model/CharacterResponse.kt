package com.example.marvelcompose.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse (
    @Json(name = "copyright") val copyright: String
)