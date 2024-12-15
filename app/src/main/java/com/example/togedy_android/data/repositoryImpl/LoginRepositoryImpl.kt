package com.example.togedy_android.data.repositoryImpl

import com.example.togedy_android.data.remote.datasource.LoginRemoteDataSource
import com.example.togedy_android.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

}