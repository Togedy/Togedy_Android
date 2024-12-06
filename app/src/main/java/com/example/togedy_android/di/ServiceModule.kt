package com.example.togedy_android.di

import com.example.togedy_android.data.remote.service.CommunityService
import com.example.togedy_android.di.qualifier.Togedy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideCommunityService(@Togedy retrofit: Retrofit): CommunityService =
        retrofit.create(CommunityService::class.java)
}
