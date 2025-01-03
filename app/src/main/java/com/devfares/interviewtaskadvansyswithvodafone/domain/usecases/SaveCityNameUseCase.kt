package com.devfares.interviewtaskadvansyswithvodafone.domain.usecases

import com.devfares.interviewtaskadvansyswithvodafone.data.sources.local.DataStoreManager
import javax.inject.Inject

class SaveCityNameUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend operator fun invoke(city: String) {
        dataStoreManager.saveLastSearchedCity(city)
    }
}
