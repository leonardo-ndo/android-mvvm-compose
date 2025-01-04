package br.com.lno.android_mvvm_compose.domain.usecase

import br.com.lno.android_mvvm_compose.domain.model.Country
import br.com.lno.android_mvvm_compose.domain.repository.CountriesRepository
import br.com.lno.android_mvvm_compose.domain.usecase.base.BaseUseCaseWithParam
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(private val countriesRepository: CountriesRepository) :
    BaseUseCaseWithParam<String, List<Country>> {

    override suspend fun execute(param: String): List<Country> {
        return countriesRepository.getCountries(continentCode = param).getOrThrow().map {
            Country(name = it.name, currency = it.currency)
        }
    }
}