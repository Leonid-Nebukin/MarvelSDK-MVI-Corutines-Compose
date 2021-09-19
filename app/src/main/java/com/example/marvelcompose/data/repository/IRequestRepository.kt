package com.example.marvelcompose.data.repository

import com.example.marvelcompose.data.model.CharacterResponse

interface IRequestRepository {

    suspend fun characters(): CharacterResponse

}