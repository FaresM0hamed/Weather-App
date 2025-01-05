package com.devfares.interviewtaskadvansyswithvodafone.domain.entities

import androidx.annotation.Keep

@Keep
sealed class DomainResult<T> {
    data class Success<T>(val data: T,val message: String? = null) : DomainResult<T>()
    data class Error<T>(val message: String? = null) : DomainResult<T>()
    class UnAuthorized<T> : DomainResult<T>()
    class UnKnownError<T> : DomainResult<T>()
    class Nothing<T> : DomainResult<T>()
}