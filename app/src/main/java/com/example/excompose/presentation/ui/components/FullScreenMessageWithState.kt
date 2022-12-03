package com.example.excompose.presentation.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excompose.R
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme
import com.example.excompose.presentation.ui.theme.ArsenalThemeExtended

@Composable
fun FullScreenMessageWithState(
    @DrawableRes icon: Int? = null,
    @ColorRes iconTint: Int? = null,
    title: String,
    message: String,
    topButtonText: String? = null,
    topButtonAction: () -> Unit = {},
    bottomButtonText: String,
    bottomButtonAction: () -> Unit = {}
) {

    //manter os dados se houver uma mudança de state
    //criamos um data class com os dados que queremos salvar se ouver mudança
    //para poderusar "rememberSaveable"
    val state by remember {
        mutableStateOf(
            MessageState(
                icon = icon,
                iconTint = iconTint,
                title = title,
                message = message,
            )
        )
    }

    Surface {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(state = rememberScrollState())
                    .weight(weight = 1f, fill = true)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                state.icon?.let {
                    Image(
                        modifier = Modifier
                            .width(70.dp)
                            .height(70.dp),
                        painter = painterResource(id = it),
                        contentDescription = "",
                        colorFilter = if (state.iconTint != null) ColorFilter.tint(colorResource(id = state.iconTint!!)) else null
                    )
                }

                Text(
                    text = state.title,
                    style = ArsenalThemeExtended.typography.body1,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = state.message,
                    style = ArsenalThemeExtended.typography.body1,
                    modifier = Modifier.padding(top = 16.dp, bottom = 36.dp),
                    textAlign = TextAlign.Center
                )

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    if (topButtonText != null) {
                        TopButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = topButtonText,
                            onClick = { topButtonAction() })
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    BottomButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = bottomButtonText,
                        onClick = { bottomButtonAction() })
                }
            }
        }
    }
}

private data class MessageState(
    val icon: Int? = null,
    val iconTint: Int? = null,
    val title: String,
    val message: String
)

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun FullscreenMsgWithStatePreview() {
    ArsenalBasicTheme {
        FullScreenMessageWithState(
            icon = R.drawable.marvel_logo,
            title = "Titulo",
            message = "Message",
            bottomButtonText = "OK",
            topButtonText = "Cancel"
        )
    }
}
