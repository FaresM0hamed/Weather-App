package com.devfares.interviewtaskadvansyswithvodafone.domain.usecases

import com.devfares.interviewtaskadvansyswithvodafone.data.sources.local.DataStoreManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class LoadLastSearchedCityWeatherUseCaseTest {

    private val dataStoreManager: DataStoreManager = mock()
    private val useCase = LoadLastSearchedCityWeatherUseCase(dataStoreManager)

    @Test
    fun `invoke should return the last searched city`() = runTest {
        val cityName = "Cairo"
        whenever(dataStoreManager.lastSearchedCity).thenReturn(flowOf(cityName))

        var result: String? = null
        useCase { result = it }

        assertEquals(cityName, result)
    }
}