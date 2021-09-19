package com.example.marvelcompose.utils.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelcompose.ui.BaseActivity

inline fun <reified T : ViewModel> BaseActivity.withViewModel(
    body: T.() -> Unit
): T {
    val vm = ViewModelProvider(this, viewModelFactory)[T::class.java]
    vm.body()
    return vm
}