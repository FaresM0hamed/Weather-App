package com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.endpoint

import com.devfares.interviewtaskadvansyswithvodafone.BuildConfig
import com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.model.BaseApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("days") days: Int=7,
        @Query("key") key: String= BuildConfig.API_KEY
    ): Response<BaseApiResponse>
}