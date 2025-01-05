package com.devfares.interviewtaskadvansyswithvodafone.domain.entities

import androidx.annotation.Keep

@Keep
data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)