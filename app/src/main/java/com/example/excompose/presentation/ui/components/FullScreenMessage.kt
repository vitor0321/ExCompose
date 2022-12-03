package com.example.excompose.presentation.ui.components

import android.content.res.Configuration
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
fun FullScreenMessage(
    @DrawableRes icon: Int? = null,
    @ColorRes iconTint: Int? = null,
    title: String,
    message: String,
    topButtonText: String? = null,
    topButtonAction: () -> Unit = {},
    bottomButtonText: String,
    bottomButtonAction: () -> Unit = {}
) {
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
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                icon?.let {
                    Image(
                        modifier = Modifier
                            .width(70.dp)
                            .height(70.dp),
                        painter = painterResource(id = it),
                        contentDescription = "",
                        colorFilter = if (iconTint != null) ColorFilter.tint(colorResource(id = iconTint)) else null
                    )
                }

                Text(
                    text = title,
                    style = ArsenalThemeExtended.typography.body1,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = message,
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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FullscreenMsgPreview() {
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