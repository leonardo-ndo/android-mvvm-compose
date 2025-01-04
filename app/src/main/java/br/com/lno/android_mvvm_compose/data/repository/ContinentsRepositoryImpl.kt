package br.com.lno.android_mvvm_compose.data.repository

import br.com.lno.android_mvvm_compose.data.model.Continent
import br.com.lno.android_mvvm_compose.data.network.ContinentsGqlDataSource
import br.com.lno.android_mvvm_compose.domain.repository.ContinentsRepository
import javax.inject.Inject

class ContinentsRepositoryImpl @Inject constructor(private val continentsGqlDataSource: ContinentsGqlDataSource) :
    ContinentsRepository {

    override suspend fun getContinents(): Result<List<Continent>> {
        return try {
            Result.success(value = continentsGqlDataSource.getContinents())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(exception = e)
        }
    }
}