package com.example.marvelcompose.di.modules

import com.example.marvelcompose.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModules {

    @ActivityScope
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity
}