package com.example.marvelcompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvelcompose.data.api.ApiRequest
import com.example.marvelcompose.data.model.CharacterPagingSource
import com.example.marvelcompose.data.model.CharacterResponse
import com.example.marvelcompose.data.model.Star
import com.example.marvelcompose.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val apiRequest: ApiRequest,
): ViewModel() {

    val characterList: Flow<PagingData<Star>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            CharacterPagingSource(apiRequest = apiRequest)
        }
    ).flow.cachedIn(viewModelScope)
}

sealed class CharactersList: UIState() {
    class Success(val data : CharacterResponse) : UIState()
    object Empty : UIState()
}