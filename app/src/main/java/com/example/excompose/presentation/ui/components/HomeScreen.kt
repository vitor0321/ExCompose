package com.example.excompose.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excompose.R
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    rowData: List<ProfileImageItem> = emptyList(),
    gridViewData: List<SimpleCardItem> = emptyList()
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
    ) {
        SearchTopBar(
            currentSearchText = "",
            onSearchTextChanged = {},
            onSearchDeactivated = {},
            onSearchDispatched = {}
        )
        HomeSection(title = R.string.app_name) {
            ProfileRow(
                rowData = rowData,
                modifier = Modifier.padding(8.dp)
            )
        }
        HomeSection(title = R.string.app_name) {
            Gridview(
                gridViewData = gridViewData,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeContentPreview() {
    ArsenalBasicTheme {
        HomeContent(
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
            gridViewData = listOf(
                SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome,1),
                SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome,2),
                SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome,3),
                SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome,4),
                SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome,5),
                SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome,6),
                SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome,7),
            )
        )
    }
}