package br.com.lno.android_mvvm_compose.data.network.gql

import br.com.lno.android_mvvm_compose.FindCountriesOfAContinentQuery
import br.com.lno.android_mvvm_compose.GetContinentsQuery
import br.com.lno.android_mvvm_compose.domain.model.Continent
import br.com.lno.android_mvvm_compose.domain.model.Country

object GQLMapper {
    fun List<GetContinentsQuery.Continent>?.toContinentList(): List<Continent> {
        return this?.map { Continent(code = it.code, name = it.name) } ?: emptyList()
    }

    fun List<FindCountriesOfAContinentQuery.Country>?.toCountryList(): List<Country> {
        return this?.map { Country(name = it.name) } ?: emptyList()
    }
}