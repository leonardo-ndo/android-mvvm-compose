package br.com.lno.android_mvvm_compose.domain.usecase

import br.com.lno.android_mvvm_compose.data.model.Continent
import br.com.lno.android_mvvm_compose.domain.repository.ContinentsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Assert
import org.junit.Test

/**
 * Tests for [GetContinentsUseCase].
 */
class GetContinentsUseCaseTest {

    private val continentsRepository = mockk<ContinentsRepository>()

    private val getContinentsUseCase =
        GetContinentsUseCase(continentsRepository = continentsRepository)

    @Test
    fun `getContinentsUseCase test`() = runTest {
        // given
        val response = listOf(
            Continent(code = "AR", name = "Africa"),
            Continent(code = "AN", name = "Antarctica"),
            Continent(code = "AS", name = "Asia"),
            Continent(code = "EU", name = "Europe"),
            Continent(code = "NA", name = "North America"),
        )

        coEvery { continentsRepository.getContinents() } returns Result.success(response)

        // when
        val result = getContinentsUseCase.execute().toList()

        // then
        Assert.assertEquals(
            result,
            response.map {
                br.com.lno.android_mvvm_compose.domain.model.Continent(
                    code = it.code,
                    name = it.name
                )
            }
        )
    }

    @Test
    fun `getContinentsUseCase exception test`() {
        // given
        coEvery { continentsRepository.getContinents() } returns Result.failure(exception = IOException())

        // when / then
        Assert.assertThrows(IOException::class.java) {
            runTest { getContinentsUseCase.execute() }
        }
    }
}