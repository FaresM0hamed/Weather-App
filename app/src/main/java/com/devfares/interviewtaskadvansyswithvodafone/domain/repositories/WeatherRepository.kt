package com.devfares.interviewtaskadvansyswithvodafone.domain.repositories

import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.DomainResult
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.MainResponse

interface WeatherRepository {
    suspend fun getWeather(city: String): DomainResult<MainResponse>
}