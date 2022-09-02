package com.example.excompose.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excompose.R
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme
import com.example.excompose.presentation.ui.theme.ArsenalThemeExtended


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingsScreenAnimated(viewModel: SettingsViewModel) {
    val items by viewModel.items.collectAsState()
    val registerID by viewModel.registerId.collectAsState()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 32.dp)
    ) {
        Text(
            text = "Configurações",
            style = ArsenalThemeExtended.typography.h1,
            modifier = Modifier.padding(start = 32.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (items.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.padding(8.dp)
            ) {
                stickyHeader {
                    Text(
                        text = "Ativação",
                        style = ArsenalThemeExtended.typography.h1,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxWidth()
                            .padding(start = 32.dp, top = 16.dp, bottom = 16.dp)
                    )
                }
                items(items = items) { item ->
                    SettingsItemAnimated(item) { viewModel.navigationTo(item.id) }
                    Spacer(modifier = Modifier.height(12.dp))
                    if (viewModel.getLastItemId() == item.id && registerID.isNotEmpty()) {
                        RegisterId(registerID)
                    }
                }
            }
        } else {
            SettingsEmptyItem()
            if (registerID.isNotEmpty()) {
                RegisterId(registerID)
            }
        }
    }
}

@Composable
private fun RegisterId(
    id: String
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()
    ) {
        Spacer(
            modifier = Modifier
                .height(12.dp)
                .weight(1F)
        )
        val bindingIdText = stringResource(R.string.register_id)
        Spacer(modifier = Modifier.height(8.dp))
        RegisterIdText(bindingIdText)
        RegisterIdText(id)
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsItemAnimated(
    item: Item,
    onItemClicked: () -> Unit = {}
) {

    val expanded = remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(0.dp),
        border = BorderStroke(1.dp, colorResource(R.color.light_seed)),
        modifier = Modifier.padding(start = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_verified),
                    contentDescription = "certified icon",
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxSize(),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Column(modifier = Modifier
                .weight(1F)
                .clickable { onItemClicked() }
            ) {
                Text(
                    text = item.title,
                    style = ArsenalThemeExtended.typography.h2
                )
                AnimatedVisibility(visible = expanded.value){
                    Column {
                        Text(
                            text = item.activated,
                            style = ArsenalThemeExtended.typography.body1
                        )
                        Text(
                            text = item.lastLogin,
                            style = ArsenalThemeExtended.typography.body2
                        )
                    }
                }

//                AnimatedVisibility(
//                    visible = expanded.value,
//                    enter = fadeIn(animationSpec = tween(250)) +
//                            expandVertically(
//                                animationSpec = tween( 500,
//                                easing = FastOutLinearInEasing)
//                            ),
//                    exit = fadeOut(animationSpec = tween(500)) +
//                            shrinkVertically(
//                                animationSpec = tween(500,
//                                easing = FastOutLinearInEasing)
//                            )
//                ){
//                    Column {
//                        Text(
//                            text = item.activated,
//                            style = ArsenalThemeExtended.typography.body1
//                        )
//                        Text(
//                            text = item.lastLogin,
//                            style = ArsenalThemeExtended.typography.body2
//                        )
//                    }
//                }
            }

            Box(modifier = Modifier.width(50.dp)) {
                OutlinedButton(
                    onClick = { expanded.value = !expanded.value },
                    modifier = Modifier.padding(8.dp),
                    shape = RoundedCornerShape(2.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = if (expanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "esconder ou mostra",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}

@Composable
private fun RegisterIdText(textString: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = textString,
        style = ArsenalThemeExtended.typography.body2.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp
        ),
    )
}


@Preview
@Composable
fun PreviewSettingsEmptyItemAnimated() {
    ArsenalBasicTheme {
        SettingsEmptyItem()
    }
}

@Preview
@Composable
fun PreviewEmptySettingsScreenAnimated() {
    ArsenalBasicTheme {
        val viewModel = SettingsViewModel()
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewDarkSettingsEmptyItemAnimated() {
    ArsenalBasicTheme(useDarkTheme = true) {
        SettingsEmptyItem()
    }
}

@Preview
@Composable
fun PreviewDarkEmptySettingsScreenAnimated() {
    ArsenalBasicTheme(useDarkTheme = false) {
        val viewModel = SettingsViewModel()
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewSettingsScreenAnimated() {
    ArsenalBasicTheme {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(Item(1, "R123456", "Aug. 2022", "Sept. 2022", "AAAAA"))
        }
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewMultiItemsSettingsScreenAnimated() {
    ArsenalBasicTheme {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(
                Item(1, "Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(2, "Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(3, "Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA")
            )
        }
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewDarkSettingsScreenAnimated() {
    ArsenalBasicTheme(useDarkTheme = true) {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(Item(1, "Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA"))
        }
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewMultiItemsDarkSettingsScreenAnimated() {
    ArsenalBasicTheme(useDarkTheme = true) {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(
                Item(1, "Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(2, "Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(3, "Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA")
            )
        }
        SettingsScreen(viewModel)
    }
}