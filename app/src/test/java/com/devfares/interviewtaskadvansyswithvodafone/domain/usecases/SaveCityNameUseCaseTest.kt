package com.devfares.interviewtaskadvansyswithvodafone.domain.usecases

import com.devfares.interviewtaskadvansyswithvodafone.data.sources.local.DataStoreManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class SaveCityNameUseCaseTest {

    private val dataStoreManager: DataStoreManager = mock()
    private val useCase = SaveCityNameUseCase(dataStoreManager)

    @Test
    fun `invoke should save the city name`() = runTest {
        val cityName = "Cairo"
        useCase(cityName)

        verify(dataStoreManager).saveLastSearchedCity(cityName)
    }
}
