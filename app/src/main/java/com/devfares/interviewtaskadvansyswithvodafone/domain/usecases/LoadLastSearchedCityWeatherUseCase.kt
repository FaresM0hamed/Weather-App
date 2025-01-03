package com.devfares.interviewtaskadvansyswithvodafone.domain.usecases

import com.devfares.interviewtaskadvansyswithvodafone.data.sources.local.DataStoreManager
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.DomainResult
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.MainResponse
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class LoadLastSearchedCityWeatherUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend operator fun invoke(onWeatherLoaded: (String?) -> Unit) {
        dataStoreManager.lastSearchedCity.collectLatest { city ->
            onWeatherLoaded(city)
        }
    }
}
