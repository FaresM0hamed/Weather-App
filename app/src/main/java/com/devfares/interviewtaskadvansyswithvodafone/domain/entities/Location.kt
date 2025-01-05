package com.devfares.interviewtaskadvansyswithvodafone.domain.entities

import androidx.annotation.Keep

@Keep
data class Location(
    val name: String,
    val region: String,
    val country: String,
)