package br.com.lno.android_mvvm_compose.domain.usecase

import br.com.lno.android_mvvm_compose.domain.model.Country
import br.com.lno.android_mvvm_compose.domain.repository.CountriesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Assert
import org.junit.Test

/**
 * Tests for [GetCountriesUseCaseTest].
 */
class GetCountriesUseCaseTest {

    private val countriesRepository = mockk<CountriesRepository>()

    private val getCountriesUseCase = GetCountriesUseCase(countriesRepository = countriesRepository)

    @Test
    fun `getCountriesUseCase test`() = runTest {
        // given
        val continentCode = "AF"

        val response = listOf(
            Country(name = "Africa"),
            Country(name = "Antarctica"),
            Country(name = "Asia"),
            Country(name = "Europe"),
            Country(name = "North America"),
        )

        coEvery { countriesRepository.getCountries(continentCode = any()) } returns response

        // when
        val result = getCountriesUseCase.execute(param = continentCode).toList()

        // then
        Assert.assertEquals(
            result,
            response.map {
                Country(name = it.name)
            }
        )
    }

    @Test
    fun `getCountriesUseCase exception test`() {
        // given
        val continentCode = "AF"

        coEvery { countriesRepository.getCountries(continentCode = any()) } throws IOException()

        // when / then
        Assert.assertThrows(IOException::class.java) {
            runTest { getCountriesUseCase.execute(param = continentCode) }
        }
    }
}