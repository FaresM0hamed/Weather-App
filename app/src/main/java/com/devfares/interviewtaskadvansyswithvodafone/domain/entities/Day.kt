package com.devfares.interviewtaskadvansyswithvodafone.domain.entities

import androidx.annotation.Keep

@Keep
data class Day(
    val condition: Condition?,
    val maxTempC: Double,
    val minTempC: Double,
)