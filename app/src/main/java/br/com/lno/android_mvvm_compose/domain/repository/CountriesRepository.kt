package br.com.lno.android_mvvm_compose.domain.repository

import br.com.lno.android_mvvm_compose.domain.model.Country

interface CountriesRepository {
    suspend fun getCountries(continentCode: String): List<Country>
}