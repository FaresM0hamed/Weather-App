package com.devfares.interviewtaskadvansyswithvodafone.domain.usecases

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidateCityNameUseCaseTest {

    private val useCase = ValidateCityNameUseCase()

    @Test
    fun `invoke should return invalid for blank city name`() {
        val result = useCase("")
        assertFalse(result.isValid)
        assertEquals("City name cannot be blank!", result.errorMessage)
    }

    @Test
    fun `invoke should return valid for non-blank city name`() {
        val result = useCase("Cairo")
        assertTrue(result.isValid)
    }
}
