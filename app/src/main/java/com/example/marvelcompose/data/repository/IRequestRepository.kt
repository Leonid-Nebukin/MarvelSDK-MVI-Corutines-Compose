package com.example.marvelcompose.data.repository

import androidx.paging.PagingData
import com.example.marvelcompose.data.model.Star
import kotlinx.coroutines.flow.Flow

interface IRequestRepository {
    fun characters(): Flow<PagingData<Star>>
}