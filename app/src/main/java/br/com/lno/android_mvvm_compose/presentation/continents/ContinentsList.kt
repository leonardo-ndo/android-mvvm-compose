package br.com.lno.android_mvvm_compose.presentation.continents

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.lno.android_mvvm_compose.domain.model.Continent
import br.com.lno.android_mvvm_compose.presentation.theme.AndroidmvvmcomposeTheme

@Composable
fun ContinentsList(continents: List<Continent>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(continents) { continent ->
            Text(text = continent.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainContentPreview() {
    AndroidmvvmcomposeTheme {
        ContinentsList(
            continents = listOf(
                Continent(code = "AR", name = "Africa"),
                Continent(code = "AN", name = "Antarctica"),
                Continent(code = "AS", name = "Asia"),
                Continent(code = "EU", name = "Europe"),
                Continent(code = "NA", name = "North America"),
            )
        )
    }
}