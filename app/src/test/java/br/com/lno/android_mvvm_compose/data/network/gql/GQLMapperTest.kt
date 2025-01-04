package br.com.lno.android_mvvm_compose.data.network.gql

import br.com.lno.android_mvvm_compose.FindCountriesOfAContinentQuery
import br.com.lno.android_mvvm_compose.GetContinentsQuery
import br.com.lno.android_mvvm_compose.data.network.gql.GQLMapper.toContinentList
import br.com.lno.android_mvvm_compose.data.network.gql.GQLMapper.toCountryList
import br.com.lno.android_mvvm_compose.domain.model.Continent
import br.com.lno.android_mvvm_compose.domain.model.Country
import org.junit.Assert
import org.junit.Test

class GQLMapperTest {

    @Test
    fun toContinentListTest() {
        // given
        val list = listOf(
            GetContinentsQuery.Continent(code = "AR", name = "Africa"),
            GetContinentsQuery.Continent(code = "AN", name = "Antarctica"),
            GetContinentsQuery.Continent(code = "AS", name = "Asia"),
            GetContinentsQuery.Continent(code = "EU", name = "Europe"),
            GetContinentsQuery.Continent(code = "NA", name = "North America"),
        )

        val expected = listOf(
            Continent(code = "AR", name = "Africa"),
            Continent(code = "AN", name = "Antarctica"),
            Continent(code = "AS", name = "Asia"),
            Continent(code = "EU", name = "Europe"),
            Continent(code = "NA", name = "North America"),
        )

        // when
        val result = list.toContinentList()

        // then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun toCountryListTest() {
        // given
        val list = listOf(
            FindCountriesOfAContinentQuery.Country(name = "Argentina"),
            FindCountriesOfAContinentQuery.Country(name = "Bolivia"),
            FindCountriesOfAContinentQuery.Country(name = "Brazil"),
            FindCountriesOfAContinentQuery.Country(name = "Chile"),
            FindCountriesOfAContinentQuery.Country(name = "Colombia"),
        )

        val expected = listOf(
            Country(name = "Argentina"),
            Country(name = "Bolivia"),
            Country(name = "Brazil"),
            Country(name = "Chile"),
            Country(name = "Colombia"),
        )

        // when
        val result = list.toCountryList()

        // then
        Assert.assertEquals(expected, result)
    }
}