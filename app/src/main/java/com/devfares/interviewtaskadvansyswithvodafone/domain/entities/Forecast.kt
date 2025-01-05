package com.devfares.interviewtaskadvansyswithvodafone.domain.entities

import androidx.annotation.Keep

@Keep
data class Forecast(
    val forecastDay: List<ForecastDay>
)