package com.example.excompose.presentation.ui.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excompose.R
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme

data class ProfileImageItem(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    val id: Int = 0
)

@Composable
fun ProfileRow(
    modifier: Modifier = Modifier,
    rowData: List<ProfileImageItem> = emptyList()
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(rowData){ item ->
            ProfileImage(item.drawable, item.text){
                Log.d("Testando", "Navegar para o item ${item.id}")
            }
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ProfileRowPreview(){
    ArsenalBasicTheme {
        ProfileRow(
            rowData = listOf(
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 1),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 2),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 3),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 4),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 5),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 6),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 7),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 8),
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}