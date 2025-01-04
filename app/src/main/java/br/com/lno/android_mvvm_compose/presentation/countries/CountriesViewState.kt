package br.com.lno.android_mvvm_compose.presentation.countries

import br.com.lno.android_mvvm_compose.domain.model.Country

sealed interface CountriesViewState {
    data object Loading : CountriesViewState
    data class Success(val data: List<Country>) : CountriesViewState
    data object Failure : CountriesViewState
}