package com.devfares.interviewtaskadvansyswithvodafone.data.utilities

import android.util.Log
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.DomainResult

import com.google.gson.Gson
import retrofit2.Response
import java.io.IOException


suspend fun <T, Y> globalNetworkCall(
    action: suspend () -> Response<T>,
    mapper: (T?) -> Y,
    doAfterSuccess: ((Y) -> Unit)? = null
): DomainResult<Y> {
    return try {

        val response = action.invoke()

        val body = response.body()

        /** successful **/
        if (response.isSuccessful && body == null) {
            return DomainResult.Error(message = "Null body")
        }

        if (response.isSuccessful && body != null) {
            doAfterSuccess?.invoke(mapper(body))
            return DomainResult.Success(data = mapper(body))
        }

        /** not successful **/
        val errorBody = getBodyError(response)
        val errorMsg = errorBody.error?.message

        if (!response.isSuccessful && response.code() == 401) {
            return DomainResult.UnAuthorized()
        }

        if (!response.isSuccessful && response.code() == 405) {
            Log.e("globalNetworkCall:", "check POST / GET")
            return DomainResult.UnKnownError()
        }

        if (!response.isSuccessful && response.code()/100 == 5) {
            return DomainResult.Error(message = "Server error")
        }

        if (!response.isSuccessful) {
            return DomainResult.Error(message = errorMsg)
        }

        Log.e("globalNetworkCall:", "check POST / GET")
        return DomainResult.Nothing()
    } catch (e: Exception) {

        Log.e("globalNetworkCall:", "message: ${e.message}\n.")
        Log.e("globalNetworkCall:", "cause: ${e.cause}\n.")
        Log.e("globalNetworkCall:", "localizedMessage: ${e.localizedMessage}\n.")

        when (e) {
            is IOException -> {
                Log.e("globalNetworkCall", "globalNetworkCall: fail")
                val exceptionMessage = e.localizedMessage ?: ""
                return if (exceptionMessage.contains("Unable to resolve host")
                    || exceptionMessage.contains("timeout")
                    || exceptionMessage.contains("Connection reset")
                    || exceptionMessage.contains("java.net.SocketException: Socket closed")
//                    || exceptionMessage.contains("Failed to connect to")
                ) {
                    DomainResult.Error(message = "Check your internet connection")
                } else if (e.cause.toString().contains("Network is unreachable")) {
                    DomainResult.Error(message = "enable wifi or mobile data")
                } else {
                    DomainResult.UnKnownError()
                }
            }
            else -> {
                DomainResult.Error(message = "else")
            }
        }
    }
}

fun <T> getBodyError(response: Response<T>): BaseApiResponse {
    return try {
        val errorResponse =
            Gson().fromJson(response.errorBody()?.charStream(), BaseApiResponse::class.java)
        errorResponse as BaseApiResponse
    } catch (exception: Exception) {
        null as BaseApiResponse
    }
}
