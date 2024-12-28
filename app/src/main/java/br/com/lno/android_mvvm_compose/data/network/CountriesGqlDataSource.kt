package br.com.lno.android_mvvm_compose.data.network

import br.com.lno.android_mvvm_compose.FindCountriesOfAContinentQuery
import br.com.lno.android_mvvm_compose.data.model.Country
import com.apollographql.apollo3.ApolloClient
import javax.inject.Inject

class CountriesGqlDataSource @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun getCountriesOfSelectedContinent(continentCode: String): List<Country> {
        val response =
            apolloClient.query(query = FindCountriesOfAContinentQuery(code = continentCode))
                .execute()
        return response.data?.continent?.countries?.map {
            Country(name = it.name, currency = it.currency)
        } ?: run {
            emptyList()
        }
    }
}