package com.example.excompose.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excompose.R
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(modifier = Modifier.padding(top = 8.dp, start = 12.dp), text = stringResource(title))
        content()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeSectionPreview() {
    ArsenalBasicTheme {
        HomeSection(R.string.app_name) {
            ProfileRow(
                rowData = listOf(
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
