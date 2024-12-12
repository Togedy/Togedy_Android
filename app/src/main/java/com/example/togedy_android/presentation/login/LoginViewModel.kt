package com.example.togedy_android.presentation.login

import androidx.lifecycle.ViewModel
import com.example.togedy_android.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

}