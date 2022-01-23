package com.example.iainteracitvemovies.di

import com.example.iainteracitvemovies.data.repository.Repository
import com.example.iainteracitvemovies.domain.usecase.UserUseCase
import com.example.iainteracitvemovies.domain.usecase.UserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Joao Betancourth on 22,enero,2022
 */

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideUsersUseCase(
        usersRepository: Repository
    ): UserUseCase {
        return UserUseCaseImpl(usersRepository)
    }
}