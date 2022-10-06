package com.example.excompose.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excompose.R
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme
import kotlinx.coroutines.delay

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    splashWaitTime: Long,
    onTimeOut: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val currentOnTimeout by rememberUpdatedState(newValue = onTimeOut)

        LaunchedEffect(key1 = true){
            delay(splashWaitTime)
            currentOnTimeout()
        }
        Image(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp)),
            painter = painterResource(id = R.drawable.marvel_logo),
            contentDescription = "Screen de inicialização"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LandingPageLightPreview(){
    ArsenalBasicTheme(
        useDarkTheme = true
    ) {
        LandingScreen(
            modifier = Modifier,
            splashWaitTime = 1_500L,
            onTimeOut = {}
        )
    }
}