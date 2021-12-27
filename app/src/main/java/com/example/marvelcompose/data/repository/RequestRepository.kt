package com.example.marvelcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.marvelcompose.data.api.ApiRequest
import com.example.marvelcompose.data.model.CharacterPagingSource
import com.example.marvelcompose.data.model.CharacterResponse
import com.example.marvelcompose.data.model.Star
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class RequestRepository @Inject constructor(
    private val apiRequest: ApiRequest
) : IRequestRepository {
    override fun characters(query: String?): Flow<PagingData<Star>> = Pager(
            config = PagingConfig(
                pageSize = ApiRequest.MAX_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                CharacterPagingSource(apiRequest = apiRequest, query = query)
            }
        ).flow
}