package com.example.marvelcompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.marvelcompose.data.repository.IRequestRepository
import com.example.marvelcompose.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val requestRepository: IRequestRepository
): ViewModel() {
    fun getCharacters() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = requestRepository.characters()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error"))
        }
    }
}