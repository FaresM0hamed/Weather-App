package com.devfares.interviewtaskadvansyswithvodafone.domain.usecases

import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.DomainResult
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.MainResponse
import com.devfares.interviewtaskadvansyswithvodafone.domain.repositories.WeatherRepository
import javax.inject.Inject


class FetchWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): DomainResult<MainResponse> {
        return repository.getWeather(city)
    }
}
