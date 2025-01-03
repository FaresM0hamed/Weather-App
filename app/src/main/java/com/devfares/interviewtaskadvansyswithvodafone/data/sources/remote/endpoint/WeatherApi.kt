package com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.endpoint

import com.devfares.interviewtaskadvansyswithvodafone.data.utilities.BaseApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json?key=641c83e960c54df6a62164800250201")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("days") days: Int=7,
    ): Response<BaseApiResponse>
}