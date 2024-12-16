package br.com.lno.android_mvvm_compose.presentation.continents

import br.com.lno.android_mvvm_compose.domain.model.Continent

sealed interface ContinentsViewState {
    data object Loading : ContinentsViewState
    data class Success(val data: List<Continent>) : ContinentsViewState
    data object Failure : ContinentsViewState
}