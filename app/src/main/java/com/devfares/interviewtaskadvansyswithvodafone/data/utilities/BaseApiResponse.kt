package com.devfares.interviewtaskadvansyswithvodafone.data.utilities

import com.google.gson.annotations.SerializedName

data class BaseApiResponse(
    @SerializedName("current")
    val current: CurrentDTO?=null,
    @SerializedName("forecast")
    val forecast: ForecastDTO?=null,
    @SerializedName("location")
    val location: LocationDTO?=null,
    @SerializedName("error")
    val error: ErrorDTO?=null
)


data class LocationDTO(
    @SerializedName("name")
    val name: String?=null,
    @SerializedName("region")
    val region: String?=null,
    @SerializedName("country")
    val country: String?=null,
)

data class CurrentDTO(
    @SerializedName("condition")
    val condition: ConditionDTO?=null,
    @SerializedName("temp_c")
    val tempC: Double?=null,
)

data class ForecastDTO(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDayDTO>?=null,
)

data class ConditionDTO(
    @SerializedName("code")
    val code: Int?=null,
    @SerializedName("icon")
    val icon: String?=null,
    @SerializedName("text")
    val text: String?=null
)

data class ForecastDayDTO(
    @SerializedName("date")
    val date: String?=null,
    @SerializedName("day")
    val day: DayDTO?=null,
)

data class DayDTO(
    @SerializedName("condition")
    val condition: ConditionDTO?=null,
    @SerializedName("maxtemp_c")
    val maxTempC: Double?=null,
    @SerializedName("mintemp_c")
    val minTempC: Double?=null,
)

data class ErrorDTO(
    @SerializedName("code")
    val code: Int?=null,
    @SerializedName("message")
    val message: String?=null
)

