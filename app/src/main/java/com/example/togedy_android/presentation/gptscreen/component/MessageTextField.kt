package com.example.togedy_android.presentation.gptscreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.presentation.gptscreen.ChatViewModel
import com.example.togedy_android.util.noRippleClickable

@Composable
fun  MessageTextField(
    viewModel: ChatViewModel = viewModel(),
){
    var chatText by remember { mutableStateOf("") }

    BasicTextField(
        value = chatText,
        onValueChange = { chatText = it },
        modifier = Modifier
            .imePadding()
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = TogedyTheme.colors.gray300,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 8.dp, vertical = 6.dp),
        textStyle = TogedyTheme.typography.body1R.copy(
            color = TogedyTheme.colors.gray900
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (chatText.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.home_text_hint),
                            style = TogedyTheme.typography.body1R,
                            color = TogedyTheme.colors.gray300
                        )
                    }
                    innerTextField()
                }
                Image(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = "전송 버튼",
                    modifier = Modifier
                        .background(
                            color = TogedyTheme.colors.yellow200,
                            shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 6.dp, vertical = 1.dp)
                        .noRippleClickable {
                                viewModel.postSendData(chatText)
                                chatText = ""
                        }
                )
            }
        }
    )
}
