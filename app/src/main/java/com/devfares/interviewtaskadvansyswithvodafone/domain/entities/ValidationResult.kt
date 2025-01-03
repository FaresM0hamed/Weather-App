package com.devfares.interviewtaskadvansyswithvodafone.domain.entities

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)