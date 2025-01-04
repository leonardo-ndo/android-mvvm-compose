package br.com.lno.android_mvvm_compose.domain.usecase

import br.com.lno.android_mvvm_compose.domain.model.Continent
import br.com.lno.android_mvvm_compose.domain.repository.ContinentsRepository
import br.com.lno.android_mvvm_compose.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class GetContinentsUseCase @Inject constructor(private val continentsRepository: ContinentsRepository) :
    BaseUseCase<List<Continent>> {

    override suspend fun execute(): List<Continent> {
        return continentsRepository.getContinents()
    }
}