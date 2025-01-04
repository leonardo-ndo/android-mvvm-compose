package br.com.lno.android_mvvm_compose.presentation.countries

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.lno.android_mvvm_compose.presentation.common.Error


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CountriesScreen(
    continentCode: String,
    continentName: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: CountriesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) {
        viewModel.getCountries(continent = continentCode)
    }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            with(sharedTransitionScope) {
                Text(
                    text = continentName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = continentCode),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            }
            when (val result = viewModel.countries.observeAsState().value) {
                CountriesViewState.Loading -> {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }

                is CountriesViewState.Success -> {
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
                    Error(modifier = Modifier.fillMaxSize()) { viewModel.getCountries(continent = continentCode) }
                }

                else -> Unit
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun CountriesScreenPreview() {
    SharedTransitionLayout {
        AnimatedVisibility(visible = true) {
            CountriesScreen(
                continentCode = "SA",
                continentName = "South America",
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this,
            )
        }
    }
}