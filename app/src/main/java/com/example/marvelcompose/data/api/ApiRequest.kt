package com.example.marvelcompose.data.api

import com.example.marvelcompose.BuildConfig
import com.example.marvelcompose.data.model.CharacterResponse
import com.example.marvelcompose.utils.extension.MD5
import com.example.marvelcompose.utils.extension.Timestamp
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface ApiRequest {

    @GET(CHARACTERS)
    suspend fun getCharacters(
        @Query(TS_PARAM) timeStamp: String = Date().Timestamp(),
        @Query(KEY_PARAM) key: String = BuildConfig.PUBLIC_KEY,
        @Query(HASH_PARAM) hash: String = String().MD5(timeStamp + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY)
    ): CharacterResponse

    companion object {
        const val CHARACTERS = "/v1/public/characters"

        const val TS_PARAM = "ts"
        const val KEY_PARAM = "apikey"
        const val HASH_PARAM = "hash"
    }
}