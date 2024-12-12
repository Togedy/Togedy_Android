package com.example.togedy_android.data.remote.datasourceimpl

import com.example.togedy_android.data.remote.datasource.LoginRemoteDataSource
import com.example.togedy_android.data.remote.service.LoginService
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val loginService: LoginService
) : LoginRemoteDataSource {

}