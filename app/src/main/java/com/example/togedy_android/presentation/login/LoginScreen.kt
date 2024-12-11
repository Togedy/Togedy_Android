package com.example.togedy_android.presentation.login

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

@Composable
fun LoginScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = TogedyTheme.colors.white)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.weight(1f)
        )

        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "투게디 로고",
            modifier = Modifier.size(width = 93.dp, height = 67.dp)
        )

        Text(
            text = "Togedy",
            color = TogedyTheme.colors.black,
            style = TogedyTheme.typography.headline1B
        )

        Spacer(
            modifier = Modifier.weight(2f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF9EB00),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 13.dp)
                .clickable {
                    hasKakaoLogin(context)
                },
            horizontalArrangement = Arrangement.spacedBy(
                space = 9.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_kakao),
                contentDescription = "카카오 로고"
            )

            Text(
                text = "카카오 계정으로 회원가입",
                style = TogedyTheme.typography.body1B
            )
        }

        Spacer(
            modifier = Modifier.weight(1.4f)
        )
    }
}

fun hasKakaoLogin(
    context: Context
) {
    if (AuthApiClient.instance.hasToken()) {
        UserApiClient.instance.accessTokenInfo { token, error ->
            if (error != null) {
                if (error is KakaoSdkError && error.isInvalidTokenError()) {
                    // 토큰이 유효하지 않은 경우
                    kakaoAppLogin(context = context)
                } else {
                    // 기타 에러
                    Log.e("카카오 토큰 오류", error.message.toString())
                }
            } else {
                // 리프레쉬 토큰 유효한 상태, 사용자 로그인 불필요
                Log.d("카카오 토큰", token.toString())
            }
        }
    } else {
        // 로그인 이력 없음
        kakaoAppLogin(context = context)
    }
}

fun kakaoAppLogin(
    context: Context
) {
    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        if (error != null) {
            Log.e("카카오 로그인", "카카오 로그인 실패 : ${error.message}")
            if (error.toString().contains("KakaoTalk not installed")) {
                // 앱 로그인 실패 시 웹으로 변경
                kakaoWebLogin(context)
            }
        } else if (token != null) {
            val kakaoAccessToken = token.accessToken
            val kakaoRefreshToken = token.refreshToken
            requestKakaoUserInfo(context)
            initLogin(context, kakaoAccessToken, kakaoRefreshToken)
        }
    }
}

fun kakaoWebLogin(
    context: Context
) {
    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
        if (error != null) {
            // 웹 브라우저를 통한 로그인 실패
            Log.e("카카오 로그인", "웹을 통한 로그인 실패: ${error.message}")
        } else if (token != null) {
            // 웹 브라우저를 통한 로그인 성공
            val kakaoAccessToken = token.accessToken
            val kakaoRefreshToken = token.refreshToken
            requestKakaoUserInfo(context)
            initLogin(context, kakaoAccessToken, kakaoRefreshToken)
        }
    }
}

fun requestKakaoUserInfo(
    context: Context
) {
    UserApiClient.instance.me { user, error ->
        if (error != null) {
            // 사용자 정보 요청 실패 처리
            Log.e(TAG, "사용자 정보 요청 실패: ${error.message}")
        } else if (user != null) {
            val userId = user.id
            val nickname = user.kakaoAccount?.profile?.nickname
            val isEmailVerified = user.kakaoAccount?.isEmailVerified ?: false

            // 이메일 미인증 시 동의창 띄우기
            if (!isEmailVerified) {
                UserApiClient.instance.loginWithNewScopes(
                    context,
                    listOf("account_email")
                ) { oAuthResponse, consentError ->
                    if (consentError != null) {
                        // 동의 실패 처리
                        Log.e(TAG, "동의 실패: ${consentError.message}")
                    } else {
                        // 동의 성공 처리
                        Log.i(TAG, "동의 성공")
                        // 동의창 띄운 후 추가 작업 수행, 사용자 정보 요청 등
                    }
                }

            } else {
                // 이미 이메일 인증된 사용자의 처리
                Log.i(TAG, "이미 이메일 인증된 사용자")
                // 추가 작업 수행
            }
        }
    }
}

fun initLogin(context: Context, kakaoAccessToken: String, kakaoRefreshToken: String) {
    /* viewModel.loadLogInData(kakaoToken)
    viewModel.logInData.observe(context){ token ->
        saveAccessToken(context, token!!.token)

    } */
    // viewModel 서버 jwt Token 호출, 다음 화면 이동 로직 추가 예정
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}