package com.devfares.interviewtaskadvansyswithvodafone.data.respositories

import com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.endpoint.WeatherApi
import com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.mappers.toMainResponse
import com.devfares.interviewtaskadvansyswithvodafone.data.utilities.BaseApiResponse
import com.devfares.interviewtaskadvansyswithvodafone.data.utilities.globalNetworkCall
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.DomainResult
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.MainResponse
import com.devfares.interviewtaskadvansyswithvodafone.domain.repositories.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun getWeather(city: String): DomainResult<MainResponse> {
        return globalNetworkCall(
            action = { weatherApi.getWeather(city = city) },
            mapper = { baseApiResponse ->
                baseApiResponse?.toMainResponse() ?: throw IllegalArgumentException("BaseApiResponse is null")
            }
        )
    }
}