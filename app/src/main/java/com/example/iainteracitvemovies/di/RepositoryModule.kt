package com.example.iainteracitvemovies.di

import com.example.iainteracitvemovies.data.network.APIService
import com.example.iainteracitvemovies.data.repository.Repository
import com.example.iainteracitvemovies.data.repository.RepositoryImpl
import com.example.iainteracitvemovies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Joao Betancourth on 22,enero,2022
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(
        apiService: APIService,
        dispatcherProvider: DispatcherProvider
    ): Repository {
        return RepositoryImpl(apiService, dispatcherProvider)
    }
}