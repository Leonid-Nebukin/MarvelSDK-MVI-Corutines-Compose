package com.example.marvelcompose.data.repository

import com.example.marvelcompose.data.api.ApiRequest
import com.example.marvelcompose.data.model.CharacterResponse
import javax.inject.Inject

class RequestRepository @Inject constructor(
    private val apiRequest: ApiRequest
) : IRequestRepository {
    override suspend fun characters(): CharacterResponse {
        return apiRequest.getCharacters()
    }
}