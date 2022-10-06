package com.example.excompose.presentation.ui.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excompose.BuildConfig
import com.example.excompose.R
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme

private val screns =
    listOf("Home", "Minhas Viagens", "Favoritos", "Alertas Viagens", "Minha conta")

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onClick: ((context: Context, selectionId: Int) -> Unit)? = null
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        Image(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp)),
            painter = painterResource(id = R.drawable.marvel_logo),
            contentDescription = null
        )

        screns.forEachIndexed { index, screen ->
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = if (onClick != null) Modifier.clickable {
                    onClick(context, index)
                } else Modifier,
                text = screen,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.weight(1F))

        Text(
            text = "App Version: ${BuildConfig.VERSION_NAME}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerDarkPreview() {
    ArsenalBasicTheme(
        useDarkTheme = true
    ) {
        Drawer()
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerLightPreview() {
    ArsenalBasicTheme(
        useDarkTheme = false
    ) {
        Drawer()
    }
}