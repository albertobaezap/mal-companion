package com.example.alberto.malcompanion.data.repository.util

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(val user: String, val password: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val credentials = Credentials.basic(user, password)

        val builder = request.newBuilder()
                .addHeader("Authorization", credentials)

        return chain.proceed(builder.build())
    }

}