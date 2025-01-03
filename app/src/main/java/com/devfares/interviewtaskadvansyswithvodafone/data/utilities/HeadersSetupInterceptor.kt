package com.devfares.interviewtaskadvansyswithvodafone.data.utilities

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject

//class HeadersSetupInterceptor @Inject constructor(
//    private val userLocalDataSource: UserLocalDataSource
//) : Interceptor {
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val invocation = chain.request().tag(Invocation::class.java)
//            ?: return chain.proceed(chain.request())
//        val shouldAttachAuthHeader = invocation
//            .method()
//            .annotations
//            .any { it.annotationClass == Authenticated::class }
//
//        return chain.proceed(
//            chain.request()
//                .newBuilder().apply {
//                    if (shouldAttachAuthHeader) {
//                        if (userLocalDataSource.getUserToken().isNotEmpty()) {
//                            addHeader("Authorization", userLocalDataSource.getHeaderToken())
//                            addHeader("Accept-Language", "en")
//                        }
//                    }
//                    addHeader("Accept", "application/json")
//                }
//                .build())
//    }
//}
//
//@Target(AnnotationTarget.FUNCTION)
//annotation class Authenticated