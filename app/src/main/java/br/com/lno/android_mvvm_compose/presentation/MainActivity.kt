package br.com.lno.android_mvvm_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.lno.android_mvvm_compose.presentation.continents.ContinentsError
import br.com.lno.android_mvvm_compose.presentation.continents.ContinentsList
import br.com.lno.android_mvvm_compose.presentation.continents.ContinentsViewState
import br.com.lno.android_mvvm_compose.presentation.theme.AndroidmvvmcomposeTheme
import br.com.lno.android_mvvm_compose.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidmvvmcomposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        when (val result = mainViewModel.result.collectAsState().value) {
                            ContinentsViewState.Loading -> {
                                CircularProgressIndicator()
                            }

                            ContinentsViewState.Failure -> {
                                ContinentsError { mainViewModel.getContinents() }
                            }

                            is ContinentsViewState.Success -> {
                                ContinentsList(
                                    continents = result.data,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}