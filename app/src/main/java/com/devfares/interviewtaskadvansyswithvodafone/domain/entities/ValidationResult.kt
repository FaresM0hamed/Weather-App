package com.devfares.interviewtaskadvansyswithvodafone.domain.entities

import androidx.annotation.Keep

@Keep
data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)