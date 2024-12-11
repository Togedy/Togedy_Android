package com.example.togedy_android.di

import com.example.togedy_android.data.remote.datasource.CommunityRemoteDataSource
import com.example.togedy_android.data.remote.datasource.LoginRemoteDataSource
import com.example.togedy_android.data.remote.datasourceimpl.CommunityRemoteDataSourceImpl
import com.example.togedy_android.data.remote.datasourceimpl.LoginRemoteDataSourceImpl
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

    @Binds
    @Singleton
    abstract fun bindsLoginRemoteDataSource(
        loginRemoteDataSourceImpl: LoginRemoteDataSourceImpl
    ): LoginRemoteDataSource
}