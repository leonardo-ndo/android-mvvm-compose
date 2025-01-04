package br.com.lno.android_mvvm_compose.data.repository

import br.com.lno.android_mvvm_compose.data.model.Country
import br.com.lno.android_mvvm_compose.data.network.CountriesGqlDataSource
import br.com.lno.android_mvvm_compose.domain.repository.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val countriesGqlDataSource: CountriesGqlDataSource
) : CountriesRepository {

    override suspend fun getCountries(continentCode: String): Result<List<Country>> {
        return try {
            Result.success(
                value = countriesGqlDataSource.getCountriesOfSelectedContinent(
                    continentCode = continentCode
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}