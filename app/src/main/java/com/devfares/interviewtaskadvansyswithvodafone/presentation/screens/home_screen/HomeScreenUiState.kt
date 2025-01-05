package com.devfares.interviewtaskadvansyswithvodafone.presentation.screens.home_screen

import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.Condition
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.Current
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.Day
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.Error
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.Forecast
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.ForecastDay
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.Location
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.MainResponse

data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isCityEmpty: Boolean = true,
    val weatherData: MainResponse = MainResponse(
        current = Current(
            condition = Condition(
                code = 0,
                icon = "",
                text = ""
            ),
            tempC = 0.0
        ),
        forecast = Forecast(
            forecastDay = List(0) { ForecastDay(
                date = "",
                day = Day(
                    condition = Condition(
                        code = 0,
                        icon = "",
                        text = ""
                    ),
                    maxTempC = 0.0,
                    minTempC = 0.0
                )
            ) }
        ),
        location = Location(
            name = "",
            region = "",
            country = ""
        ),
        error = Error(
            code = 0,
            message = ""
        )
    )
)

