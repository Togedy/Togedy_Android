package com.example.togedy_android.di


import com.example.togedy_android.data.repositoryImpl.CommunityRepositoryImpl
import com.example.togedy_android.data.repositoryImpl.LoginRepositoryImpl
import com.example.togedy_android.domain.repository.CommunityRepository
import com.example.togedy_android.domain.repository.LoginRepository
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
    abstract fun bindCommunityRepository(
        communityRepositoryImpl: CommunityRepositoryImpl
    ): CommunityRepository

    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository
}