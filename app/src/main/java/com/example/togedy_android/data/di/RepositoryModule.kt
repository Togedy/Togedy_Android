package com.example.togedy_android.data.di

import com.example.togedy_android.data.repositoryImpl.LogInRepositoryImpl
import com.example.togedy_android.domain.repository.LogInRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLogInRepository(
        logInRepositoryImpl: LogInRepositoryImpl
    ) : LogInRepository
}