package br.com.lno.android_mvvm_compose.data.network

import br.com.lno.android_mvvm_compose.GetContinentsQuery
import br.com.lno.android_mvvm_compose.data.model.Continent
import com.apollographql.apollo3.ApolloClient
import javax.inject.Inject

class ContinentsGqlDataSource @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun getContinents(): List<Continent> {
        val response = apolloClient.query(query = GetContinentsQuery()).execute()
        return response.data?.continents?.map {
            Continent(code = it.code, name = it.name)
        } ?: run {
            emptyList()
        }
    }
}