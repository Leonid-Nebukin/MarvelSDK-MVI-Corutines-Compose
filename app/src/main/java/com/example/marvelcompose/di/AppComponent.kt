package com.example.marvelcompose.di

import com.example.marvelcompose.Application
import com.example.marvelcompose.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModules::class,
        ViewModelModule::class,
        RepositoryModule::class
    ]
)

interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: android.app.Application): Builder

        fun build(): AppComponent
    }
}