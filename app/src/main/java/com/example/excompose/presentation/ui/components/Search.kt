package com.example.excompose.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.excompose.R
import com.example.excompose.presentation.ui.theme.ArsenalThemeExtended

class SearchViewModel : ViewModel() {

    private val _isShowingSearchField: MutableState<Boolean> = mutableStateOf(false)
    val isShowingSearchField: State<Boolean> = _isShowingSearchField

    private val _currentSearchText: MutableState<String> = mutableStateOf(value = "")
    val currentSearchText: State<String> = _currentSearchText

    fun showSearchField(show: Boolean) {
        _isShowingSearchField.value = show
    }

    fun setCurrentSearchText(newText: String) {
        _currentSearchText.value = newText
    }
}

@Composable
fun SearchableTopbar(
    isShowingSearchField: Boolean,
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit,
    onSearchIconClicked: () -> Unit
) {
    when (isShowingSearchField) {
        true -> SearchTopBar(
            currentSearchText = currentSearchText,
            onSearchTextChanged = onSearchTextChanged,
            onSearchDeactivated = onSearchDeactivated,
            onSearchDispatched = onSearchDispatched
        )
        else -> HomeTopBar(
            topBarNameId = R.string.compose_arsenal,
            onSearchIconClicked = onSearchIconClicked
        )
    }
}

@Composable
fun SearchTopBar(
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(60.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colorScheme.primary
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = currentSearchText,
            onValueChange = { onSearchTextChanged(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Digite aqui ...",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            textStyle = TextStyle(
                fontSize = ArsenalThemeExtended.typography.body1.fontSize,
                color = MaterialTheme.colorScheme.onPrimary
            ),
            singleLine = true,
            leadingIcon = { SearchLeadingIcon() },
            trailingIcon = {
                SearchTrailingIcon {
                    if (currentSearchText.isNotEmpty()) onSearchTextChanged("") else onSearchDeactivated()
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchDispatched(currentSearchText) }),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}

@Composable
fun HomeTopBar(
    @StringRes topBarNameId: Int = R.string.empty,
    onSearchIconClicked: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(30.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colorScheme.primary
    ) {
        TopAppBar(
            backgroundColor = MaterialTheme.colorScheme.primary,
            title = {
                Text(
                    text = stringResource(id = topBarNameId),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            actions = {
                SearchIcon(action = onSearchIconClicked)
            },
            elevation = AppBarDefaults.ContentPadding.calculateTopPadding()
        )
    }
}

@Composable
fun SearchIcon(
    action: () -> Unit = {}
) {
    DefaultIcon(
        searchIcon = Icons.Filled.Search,
        contentDescripton = "Search Icon",
        onIconClickAction = action
    )
}

@Composable
fun SearchLeadingIcon(
    action: () -> Unit = {}
) {
    DefaultIcon(
        modifier = Modifier.alpha(ContentAlpha.medium),
        onIconClickAction = action
    )
}

@Composable
fun SearchTrailingIcon(
    action: () -> Unit = {}
) {
    DefaultIcon(
        searchIcon = Icons.Default.Close,
        contentDescripton = "Deactivate Search Icon",
        onIconClickAction = action
    )
}

@Composable
fun DefaultIcon(
    modifier: Modifier = Modifier,
    searchIcon: ImageVector = Icons.Default.Search,
    iconColor: Color = Color.White,
    contentDescripton: String = "Magnifier Search Icon",
    onIconClickAction: () -> Unit = {}
) {
    IconButton(
        modifier = modifier,
        onClick = onIconClickAction
    ) {
        Icon(
            imageVector = searchIcon,
            contentDescription = contentDescripton,
            tint = iconColor
        )
    }
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar(R.string.app_name, onSearchIconClicked = {})
}

@Composable
@Preview
fun SearchTopBarPreview() {
    SearchTopBar(
        currentSearchText = "Texto de busca",
        onSearchTextChanged = {},
        onSearchDeactivated = {},
        onSearchDispatched = {}
    )
}