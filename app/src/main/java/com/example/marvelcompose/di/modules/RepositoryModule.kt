package com.example.marvelcompose.di.modules

import com.example.marvelcompose.data.repository.IRequestRepository
import com.example.marvelcompose.data.repository.RequestRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRequestRepository(repository: RequestRepository): IRequestRepository
}