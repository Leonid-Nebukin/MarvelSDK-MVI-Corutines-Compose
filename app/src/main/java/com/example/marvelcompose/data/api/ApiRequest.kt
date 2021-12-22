package com.example.marvelcompose.data.api

import androidx.annotation.IntRange
import com.example.marvelcompose.BuildConfig
import com.example.marvelcompose.data.model.CharacterResponse
import com.example.marvelcompose.utils.extension.MD5
import com.example.marvelcompose.utils.extension.Timestamp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface ApiRequest {

    @GET(CHARACTERS)
    suspend fun getCharacters(
        @Query(TS_PARAM) timeStamp: String = Date().Timestamp(),
        @Query(KEY_PARAM) key: String = BuildConfig.PUBLIC_KEY,
        @Query(HASH_PARAM) hash: String = String().MD5(timeStamp + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY),
        @Query(OFFSET_PARAM) offset: Int = DEFAULT_PAGE_SIZE,
        @Query(LIMIT_PARAM) @IntRange(from = 1, to = MAX_PAGE_SIZE.toLong()) limit: Int = DEFAULT_PAGE_SIZE
    ): Response<CharacterResponse>

    companion object {
        const val CHARACTERS = "/v1/public/characters"

        const val TS_PARAM = "ts"
        const val KEY_PARAM = "apikey"
        const val HASH_PARAM = "hash"
        const val OFFSET_PARAM = "offset"
        const val LIMIT_PARAM = "limit"

        const val DEFAULT_PAGE_SIZE = 10

        const val MAX_PAGE_SIZE = 10
    }
}