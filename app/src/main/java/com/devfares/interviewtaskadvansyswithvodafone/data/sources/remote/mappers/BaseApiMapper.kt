package com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.mappers

import com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.model.BaseApiResponse
import com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.model.ConditionDTO
import com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.model.CurrentDTO
import com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.model.DayDTO
import com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.model.ErrorDTO
import com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.model.ForecastDTO
import com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.model.ForecastDayDTO
import com.devfares.interviewtaskadvansyswithvodafone.data.sources.remote.model.LocationDTO
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.Condition
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.Current
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.Day
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.Error
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.Forecast
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.ForecastDay
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.Location
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.MainResponse

fun BaseApiResponse.toMainResponse() = MainResponse(
    current = this.current.toCurrent(),
    forecast = this.forecast.toForecast(),
    location = this.location.toLocation(),
    error = this.error.toError()
)

fun ErrorDTO?.toError() = Error(
    code = this?.code ?: 0,
    message = this?.message ?: ""
)

fun ConditionDTO?.toCondition() = Condition(
    code = this?.code ?: 0,
    icon = this?.icon ?: "",
    text = this?.text ?: ""
)

fun DayDTO?.toDay() = Day(
    condition = this?.condition?.toCondition() ,
    maxTempC = this?.maxTempC ?: 0.0,
    minTempC = this?.minTempC ?: 0.0
)

fun ForecastDayDTO?.toForecastDay() = ForecastDay(
    date = this?.date ?: "",
    day = this?.day?.toDay()
)

fun ForecastDTO?.toForecast() = Forecast(
    forecastDay = this?.forecastDay?.map { it.toForecastDay() } ?: emptyList()
)

fun CurrentDTO?.toCurrent() = Current(
    condition = this?.condition?.toCondition() ,
    tempC = this?.tempC ?: 0.0
)

fun LocationDTO?.toLocation() = Location(
    name = this?.name ?: "",
    region = this?.region ?: "",
    country = this?.country ?: ""
)