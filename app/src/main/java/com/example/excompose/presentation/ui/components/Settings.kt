package com.example.excompose.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.excompose.R
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme
import com.example.excompose.presentation.ui.theme.ArsenalThemeExtended
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class Item(
    var id: Int = 1,
    var title: String = "Titulo",
    var activated: String = "Aug. 2022",
    var lastLogin: String = "Sept. 2022",
    var serial: String = "AAAA"
)

class SettingsViewModel : ViewModel() {
    val items = MutableStateFlow<MutableList<Item>>(mutableListOf())
    val registerId = MutableStateFlow("")

    fun setItems(item: List<Item>) {
        viewModelScope.launch {
            this@SettingsViewModel.items.emit(items as MutableList<Item>)
        }
    }

    fun removeItem(item: Item) {
        items.value = items.value.filter { it != item }.toMutableList()
    }

    fun getLastItemId(): Int = items.value.last().id

    fun setRegisterId(id: String) {
        viewModelScope.launch {
            registerId.emit(id)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
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
                    SettingsItem(item) { viewModel.removeItem(item) }
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
fun SettingsItem(
    item: Item,
    onItemClicked: () -> Unit = {}
) {
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

            Column(modifier = Modifier.weight(1F)) {
                Text(
                    text = item.title,
                    style = ArsenalThemeExtended.typography.h2
                )
                Text(
                    text = item.activated,
                    style = ArsenalThemeExtended.typography.body1
                )
                Text(
                    text = item.lastLogin,
                    style = ArsenalThemeExtended.typography.body2
                )
            }

            Box(modifier = Modifier.width(50.dp)) {
                OutlinedButton(
                    onClick = { onItemClicked() },
                    modifier = Modifier.padding(8.dp),
                    shape = RoundedCornerShape(2.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        Icons.Outlined.Delete,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "deletar",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsEmptyItem() {
    Box(modifier = Modifier.padding(12.dp)) {
        Column(Modifier.background(colorResource(R.color.light_seed))) {
            Card(
                shape = RoundedCornerShape(0.dp),
                border = BorderStroke(4.dp, colorResource(R.color.light_seed)),
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ArsenalThemeExtended.colors.snowWhite)
                ) {
                    Box {
                        Image(
                            modifier = Modifier
                                .padding(start = 8.dp, top = 20.dp, end = 8.dp, bottom = 20.dp)
                                .align(Alignment.TopCenter),
                            painter = painterResource(R.drawable.ic_warning),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = colorResource(R.color.light_seed))
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 32.dp),
                        text = "A nossa lista está vazia",
                        style = ArsenalThemeExtended.typography.body1
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
fun PreviewSettingsEmptyItem() {
    ArsenalBasicTheme {
        SettingsEmptyItem()
    }
}

@Preview
@Composable
fun PreviewEmptySettingsScreen() {
    ArsenalBasicTheme {
        val viewModel = SettingsViewModel()
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewDarkSettingsEmptyItem() {
    ArsenalBasicTheme(useDarkTheme = true) {
        SettingsEmptyItem()
    }
}

@Preview
@Composable
fun PreviewDarkEmptySettingsScreen() {
    ArsenalBasicTheme(useDarkTheme = false) {
        val viewModel = SettingsViewModel()
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewSettingsScreen() {
    ArsenalBasicTheme {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(Item(1, "R123456", "Aug. 2022", "Sept. 2022", "AAAAA"))
        }
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewMultiItemsSettingsScreen() {
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
fun PreviewDarkSettingsScreen() {
    ArsenalBasicTheme(useDarkTheme = true) {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(Item(1, "Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA"))
        }
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewMultiItemsDarkSettingsScreen() {
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