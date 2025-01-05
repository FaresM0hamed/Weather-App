package com.devfares.interviewtaskadvansyswithvodafone.domain.entities

import androidx.annotation.Keep

@Keep
data class ForecastDay(
    val date: String,
    val day: Day?,
)