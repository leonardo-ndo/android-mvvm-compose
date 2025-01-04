package br.com.lno.android_mvvm_compose.data.network.gql

import br.com.lno.android_mvvm_compose.GetContinentsQuery
import br.com.lno.android_mvvm_compose.data.network.gql.GQLMapper.toContinentList
import br.com.lno.android_mvvm_compose.domain.model.Continent
import com.apollographql.apollo.ApolloClient
import javax.inject.Inject

class ContinentsGqlDataSource @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun getContinents(): List<Continent> {
        val response = apolloClient.query(query = GetContinentsQuery()).execute()
        return response.data?.continents.toContinentList()
    }
}