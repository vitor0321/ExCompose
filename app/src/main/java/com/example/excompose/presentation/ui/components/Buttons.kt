package com.example.excompose.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excompose.R
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme

@Composable
fun FavoriteButton(onClickFavorite: () -> Unit) {
    androidx.compose.material3.Button(
        onClick = onClickFavorite,
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        ),
    ) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Favorite",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text("Like", color = Color.White)
    }
}

@Composable
@Preview
fun FavoriteButtonLightPreview() {
    ArsenalBasicTheme(useDarkTheme = false) {
        FavoriteButton{}
    }
}

@Composable
@Preview
fun FavoriteButtonDarkPreview() {
    ArsenalBasicTheme(useDarkTheme = true) {
        FavoriteButton{}
    }
}

@Composable
fun FavoriteSecondButton(onClickFloating: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = onClickFloating,
        icon = {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Favorite"
            )
        },
        text = { Text("Like") }
    )
}

@Composable
@Preview
fun FavoriteSecondButtonLightPreview() {
    ArsenalBasicTheme(useDarkTheme = false) {
        FavoriteSecondButton{}
    }
}

@Composable
@Preview
fun FavoriteSecondButtonDarkPreview() {
    ArsenalBasicTheme(useDarkTheme = true) {
        FavoriteSecondButton{}
    }
}

@Composable
fun ArsenalButton(
    modifier: Modifier,
    clickAction: () -> Unit,
    @StringRes textId: Int
){
    Button(modifier = modifier, onClick = clickAction){
        Text(text = stringResource(id = textId))
    }
}

@Composable
@Preview
fun ArsenalButtonLithPreview(){
    ArsenalBasicTheme(useDarkTheme = false) {
        ArsenalButton(
            modifier = Modifier.widthIn(min = 100.dp, max = 120.dp),
            clickAction = {},
            textId = R.string.template
        )
    }
}

@Composable
@Preview
fun ArsenalButtonDarkPreview(){
    ArsenalBasicTheme(useDarkTheme = true) {
        ArsenalButton(
            modifier = Modifier.widthIn(min = 100.dp, max = 120.dp),
            clickAction = {},
            textId = R.string.template
        )
    }
}

@Composable
fun ArsenalButtonRow(
    modifier: Modifier,
    positiveAction: (() -> Unit)? = null,
    negativeAction: (() -> Unit)? = null,
    neutralAction: (() -> Unit)? = null,
    @StringRes positiveTextId: Int? = null,
    @StringRes negativeTextId: Int? = null,
    @StringRes neutralTextId: Int? = null,
){
    Row(
        Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ){
        negativeAction?.let { clickAction ->
            negativeTextId?.let { textId ->
                ArsenalButton(modifier, clickAction, textId)
            }
        }
        if (negativeAction == null) Spacer(modifier = modifier)

        positiveAction?.let { clickAction ->
            positiveTextId?.let { textId ->
                ArsenalButton(modifier, clickAction, textId)
            }
        }
        if (neutralAction == null) Spacer(modifier = modifier)

        neutralAction?.let { clickAction ->
            neutralTextId?.let { textId ->
                ArsenalButton(modifier, clickAction, textId)
            }
        }
        if (neutralAction == null) Spacer(modifier = modifier)
    }
}

@Composable
@Preview
fun ArsenalButtonLightRowPreview(){
    ArsenalBasicTheme(useDarkTheme = false) {
        ArsenalButtonRow(
            modifier = Modifier.widthIn(min = 100.dp, max = 120.dp),
            positiveAction = {},
            negativeAction = {},
            neutralAction = {},
            positiveTextId = R.string.template,
            negativeTextId = R.string.template,
            neutralTextId = R.string.template
        )
    }
}

@Composable
@Preview
fun ArsenalButtonDarkRowPreview(){
    ArsenalBasicTheme(useDarkTheme = true) {
        ArsenalButtonRow(
            modifier = Modifier.widthIn(min = 100.dp, max = 120.dp),
            positiveAction = {},
            negativeAction = {},
            neutralAction = {},
            positiveTextId = R.string.template,
            negativeTextId = R.string.template,
            neutralTextId = R.string.template
        )
    }
}