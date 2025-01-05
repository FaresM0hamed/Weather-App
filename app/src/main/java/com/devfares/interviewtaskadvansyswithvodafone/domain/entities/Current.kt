package com.devfares.interviewtaskadvansyswithvodafone.domain.entities
import androidx.annotation.Keep

@Keep
data class Current(
    val condition: Condition?,
    val tempC: Double,
)