package com.devfares.interviewtaskadvansyswithvodafone.domain.entities

import androidx.annotation.Keep

@Keep
data class MainResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location,
    val error: Error
)

