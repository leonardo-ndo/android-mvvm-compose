package br.com.lno.android_mvvm_compose.data.repository

import br.com.lno.android_mvvm_compose.data.network.gql.ContinentsGqlDataSource
import br.com.lno.android_mvvm_compose.domain.model.Continent
import br.com.lno.android_mvvm_compose.domain.repository.ContinentsRepository
import javax.inject.Inject

class ContinentsRepositoryImpl @Inject constructor(private val continentsGqlDataSource: ContinentsGqlDataSource) :
    ContinentsRepository {

    override suspend fun getContinents(): List<Continent> {
        return continentsGqlDataSource.getContinents()
    }
}