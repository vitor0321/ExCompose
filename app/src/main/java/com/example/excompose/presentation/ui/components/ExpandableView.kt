package com.example.excompose.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme
import com.example.excompose.presentation.viewModel.SavableViewModel
import com.example.excompose.presentation.viewModel.ExpandableViewModel

@Composable
fun ExpandableView() {
    val expanded = remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(vertical = 0.dp, horizontal = 8.dp)
    ) {
        DropDownComponent(
            title = "Estado simples",
            description = "Não retem o stado do componente",
            minimizeText = "menos",
            maximizeText = "mais",
            contentDescription = "botão de minimizar ou maximizar o conteúdo",
            expanded = expanded.value
        ) {
            expanded.value = !expanded.value
        }
    }
}

@Composable
fun ExpandableSavableView() {
    val expanded = rememberSaveable { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(vertical = 0.dp, horizontal = 8.dp)
    ) {
        DropDownComponent(
            title = "Estado Resistente",
            description = "rememberSaveable retém o estado através das recomposições",
            minimizeText = "menos",
            maximizeText = "mais",
            contentDescription = "botão de minimizar ou maximizar o conteúdo",
            expanded = expanded.value
        ) {
            expanded.value = !expanded.value
        }
    }
}

@Composable
fun ExpandableSavableViewHoisted(viewModel: ExpandableViewModel) {
    val expanded = viewModel.state.collectAsState().value

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(vertical = 0.dp, horizontal = 8.dp)
    ) {
        DropDownComponent(
            title = "Estado Resistente",
            description = "Resiste em todo o ciclo de vida da activity pois o valor está setatdo no viewModel, mas não resiste ainda as mudanças de configurações",
            minimizeText = "menos",
            maximizeText = "mais",
            contentDescription = "botão de minimizar ou maximizar o conteúdo",
            expanded = expanded
        ) {
            viewModel.toggleState(!expanded)
        }
    }
}

@Composable
fun ExpandableSavableViewHoistedSavable(viewModel: SavableViewModel) {

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(vertical = 0.dp, horizontal = 8.dp)
    ) {
        DropDownComponent(
            title = "Estado Resistente",
            description = "Resiste em todo o ciclo de vida da activity pois o valor está setatdo no viewModel, e ainda resiste as mudanças de configurações",
            minimizeText = "menos",
            maximizeText = "mais",
            contentDescription = "botão de minimizar ou maximizar o conteúdo",
            expanded = viewModel.saveableMutableComposeState
        ) {
            viewModel.triggerComposeState(!viewModel.saveableMutableComposeState)
        }
    }
}

@Preview
@Composable
fun ExpandableViewPreview() {
    val savedStateHandle = SavedStateHandle()
    val saveableViewModel = SavableViewModel(savedStateHandle)

    ArsenalBasicTheme(useDarkTheme = false) {
        ExpandableView()
        ExpandableSavableView()
        ExpandableSavableViewHoisted(ExpandableViewModel())
        ExpandableSavableViewHoistedSavable(saveableViewModel)
    }
}