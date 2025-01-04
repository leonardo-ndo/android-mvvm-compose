package br.com.lno.android_mvvm_compose.data.network.gql

import br.com.lno.android_mvvm_compose.FindCountriesOfAContinentQuery
import br.com.lno.android_mvvm_compose.data.network.gql.GQLMapper.toCountryList
import br.com.lno.android_mvvm_compose.domain.model.Country
import com.apollographql.apollo.ApolloClient
import javax.inject.Inject

class CountriesGqlDataSource @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun getCountriesOfSelectedContinent(continentCode: String): List<Country> {
        val response =
            apolloClient.query(query = FindCountriesOfAContinentQuery(code = continentCode))
                .execute()
        return response.data?.continent?.countries.toCountryList()
    }
}