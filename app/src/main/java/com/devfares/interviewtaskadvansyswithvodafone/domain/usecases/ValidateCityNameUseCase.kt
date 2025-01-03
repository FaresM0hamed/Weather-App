package com.devfares.interviewtaskadvansyswithvodafone.domain.usecases

import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.ValidationResult
import javax.inject.Inject

class ValidateCityNameUseCase @Inject constructor() {
    operator fun invoke(city: String): ValidationResult {
        return if (city.isBlank()) {
            ValidationResult(
                isValid = false,
                errorMessage = "City name cannot be blank!"
            )
        } else {
            ValidationResult(isValid = true)
        }
    }
}