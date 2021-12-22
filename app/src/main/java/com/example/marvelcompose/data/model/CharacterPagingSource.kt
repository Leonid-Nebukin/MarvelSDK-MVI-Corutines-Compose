package com.example.marvelcompose.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelcompose.data.api.ApiRequest
import retrofit2.HttpException

class CharacterPagingSource(
    val apiRequest: ApiRequest
) : PagingSource<Int, Star>() {
    override fun getRefreshKey(state: PagingState<Int, Star>): Int? {
        val page = state.closestPageToPosition(state.anchorPosition ?: return null)
        return page?.prevKey?.plus(ApiRequest.DEFAULT_PAGE_SIZE) ?: page?.nextKey?.minus(ApiRequest.DEFAULT_PAGE_SIZE)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Star> {
        val page = params.key ?: 0
        val pageSize = params.loadSize.coerceAtMost(ApiRequest.MAX_PAGE_SIZE)

        val response = apiRequest.getCharacters(offset = page, limit = pageSize)

        return if (response.isSuccessful) {
            val data = checkNotNull(response.body()).data.results
            val nextKey = page + data.size
            val prevKey = if (page == 0) null else page - data.size
            LoadResult.Page(data, prevKey, nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }
}