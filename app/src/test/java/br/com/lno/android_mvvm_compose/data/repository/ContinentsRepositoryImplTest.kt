package br.com.lno.android_mvvm_compose.data.repository

import br.com.lno.android_mvvm_compose.data.model.Continent
import br.com.lno.android_mvvm_compose.data.network.ContinentsGqlDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Assert
import org.junit.Test

/**
 * Tests for [ContinentsRepositoryImpl].
 */
class ContinentsRepositoryImplTest {

    private val continentsGqlDataSource = mockk<ContinentsGqlDataSource>()

    private val continentsRepository =
        ContinentsRepositoryImpl(continentsGqlDataSource = continentsGqlDataSource)

    @Test
    fun `getContinents test`() = runTest {
        // given
        val continentsResponse = listOf(
            Continent(code = "AR", name = "Africa"),
            Continent(code = "AN", name = "Antarctica"),
            Continent(code = "AS", name = "Asia"),
            Continent(code = "EU", name = "Europe"),
            Continent(code = "NA", name = "North America"),
        )

        coEvery { continentsGqlDataSource.getContinents() } returns continentsResponse

        // when
        val result = continentsRepository.getContinents()

        // then
        Assert.assertEquals(result, Result.success(continentsResponse))
        coVerify {
            continentsGqlDataSource.getContinents()
        }
    }

    @Test
    fun `getContinents exception test`() = runTest {
        // given
        val exception = IOException()

        coEvery { continentsGqlDataSource.getContinents() } throws exception

        // when
        val result = continentsRepository.getContinents()

        // then
        Assert.assertEquals(result, Result.failure<Throwable>(exception))
        coVerify {
            continentsGqlDataSource.getContinents()
        }
    }
}