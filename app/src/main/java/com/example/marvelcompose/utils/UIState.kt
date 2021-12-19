package com.example.marvelcompose.utils
open class UIState {
    object Loading : UIState()
    class Error(val error: String?) : UIState()
}