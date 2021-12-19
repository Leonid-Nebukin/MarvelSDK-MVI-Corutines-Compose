package com.example.marvelcompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcompose.data.model.CharacterResponse
import com.example.marvelcompose.data.repository.IRequestRepository
import com.example.marvelcompose.utils.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val requestRepository: IRequestRepository
): ViewModel() {
    val characterList: MutableStateFlow<UIState> = MutableStateFlow(CharactersList.Empty)

    fun getCharacters() = viewModelScope.launch(Dispatchers.IO) {
        characterList.value = UIState.Loading
        try {
            characterList.value = CharactersList.Success(requestRepository.characters())
        } catch (exception: Exception) {
            characterList.value = UIState.Error(exception.message)
        }
    }

}

sealed class CharactersList: UIState() {
    class Success(val data : CharacterResponse) : UIState()
    object Empty : UIState()
}