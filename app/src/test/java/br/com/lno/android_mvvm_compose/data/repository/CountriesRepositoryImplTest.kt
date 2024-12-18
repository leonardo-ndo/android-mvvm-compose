package br.com.lno.android_mvvm_compose.data.repository

import br.com.lno.android_mvvm_compose.data.network.CountriesGqlDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import java.io.IOException

/**
 * Tests for [CountriesRepositoryImpl].
 */
class CountriesRepositoryImplTest {

    private val countriesGqlDataSource = mockk<CountriesGqlDataSource>()

    private val countriesRepository =
        CountriesRepositoryImpl(countriesGqlDataSource = countriesGqlDataSource)

    @Test
    fun `getCountries test`() = runTest {
        // given
        val continentCode = "BR"

        coEvery { countriesGqlDataSource.getCountriesOfSelectedContinent(continentCode = any()) } returns listOf()

        // when
        countriesRepository.getCountries(continentCode = continentCode)

        // then
        coVerify {
            countriesGqlDataSource.getCountriesOfSelectedContinent(continentCode)
        }
    }

    @Test
    fun `getContinents exception test`() {
        // given
        val continentCode = "BR"

        coEvery { countriesGqlDataSource.getCountriesOfSelectedContinent(continentCode = any()) } throws IOException()

        // when / then
        Assert.assertThrows(IOException::class.java) {
            runTest { countriesRepository.getCountries(continentCode = continentCode) }
        }
    }
}