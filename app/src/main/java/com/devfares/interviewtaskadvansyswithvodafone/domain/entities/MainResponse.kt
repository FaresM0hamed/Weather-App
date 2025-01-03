package com.devfares.interviewtaskadvansyswithvodafone.domain.entities

data class MainResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location,
    val error: Error
)


data class Location(
    val name: String,
    val region: String,
    val country: String,
)

data class Current(
    val condition: Condition?,
    val tempC: Double,
)

data class Forecast(
    val forecastDay: List<ForecastDay>
)

data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)

data class ForecastDay(
    val date: String,
    val day: Day?,
)

data class Day(
    val condition: Condition?,
    val maxTempC: Double,
    val minTempC: Double,
)

data class Error(
    val code: Int,
    val message: String
)
