package br.com.lno.android_mvvm_compose.domain.usecase

import br.com.lno.android_mvvm_compose.domain.repository.ContinentsRepository
import br.com.lno.android_mvvm_compose.presentation.continents.ContinentsViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetContinentsUseCase @Inject constructor(private val continentsRepository: ContinentsRepository) {

    fun execute(): Flow<ContinentsViewState> {
        return flow {
            emit(ContinentsViewState.Loading)
            try {
                val response = continentsRepository.getContinents()
                emit(ContinentsViewState.Success(data = response))
            } catch (e: Exception) {
                emit(ContinentsViewState.Failure)
            }
        }.flowOn(Dispatchers.IO)
    }
}