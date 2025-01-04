package br.com.lno.android_mvvm_compose.presentation.countries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.lno.android_mvvm_compose.presentation.common.Error

@Composable
fun CountriesScreen(
    continentCode: String,
    continentName: String,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getCountries(continent = continentCode)
    }

    Column(verticalArrangement = Arrangement.Center) {
        when (val result = viewModel.countries.observeAsState().value) {
            CountriesViewState.Loading -> {
                CircularProgressIndicator()
            }

            is CountriesViewState.Success -> {
                Text(
                    text = continentName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(result.data) {
                        Text(
                            text = it.name, modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 6.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            CountriesViewState.Failure -> {
                Error { viewModel.getCountries(continent = continentCode) }
            }

            else -> Unit
        }
    }
}

@Preview
@Composable
fun CountriesScreenPreview() {
    CountriesScreen(continentCode = "SA", continentName = "South America")
}