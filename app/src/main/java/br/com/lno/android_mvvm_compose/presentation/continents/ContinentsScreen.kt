package br.com.lno.android_mvvm_compose.presentation.continents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.lno.android_mvvm_compose.domain.model.Continent
import br.com.lno.android_mvvm_compose.presentation.common.Error

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ContinentsScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (Continent) -> Unit,
    viewModel: ContinentsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) {
        viewModel.getContinents()
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (val result = viewModel.result.observeAsState().value) {
            ContinentsViewState.Loading -> {
                CircularProgressIndicator()
            }

            is ContinentsViewState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(result.data) {
                        with(sharedTransitionScope) {
                            Text(
                                text = it.name,
                                modifier = Modifier
                                    .clickable { onItemClick(it) }
                                    .sharedElement(
                                        state = rememberSharedContentState(key = it.code),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                    )
                                    .fillMaxWidth()
                                    .padding(all = 6.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            ContinentsViewState.Failure -> {
                Error { viewModel.getContinents() }
            }

            else -> Unit
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun ContinentsScreenPreview() {
    SharedTransitionLayout {
        AnimatedVisibility(visible = true) {
            ContinentsScreen(
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this,
                onItemClick = { }
            )
        }
    }
}