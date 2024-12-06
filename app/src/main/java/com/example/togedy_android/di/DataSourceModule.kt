package com.example.togedy_android.di

import com.example.togedy_android.data.remote.datasource.CommunityRemoteDataSource
import com.example.togedy_android.data.remote.datasourceimpl.CommunityRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsCommunityRemoteDataSource(
        communityRemoteDataSourceImpl: CommunityRemoteDataSourceImpl
    ): CommunityRemoteDataSource
}