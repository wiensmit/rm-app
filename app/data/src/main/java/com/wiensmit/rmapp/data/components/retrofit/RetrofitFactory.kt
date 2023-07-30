package com.wiensmit.rmapp.data.components.retrofit

import com.pluto.plugins.network.PlutoInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * factory to create retrofit api instances
 *
 * version 1.0
 */
@Singleton
class RetrofitFactory @Inject constructor() {

    companion object {
        private const val NETWORK_TIMEOUT = 30
    }

    fun buildWithUrl(
        baseUrl: String,
        enableNetworkLogging: Boolean,
    ): Retrofit {
        return Retrofit.Builder().run {
            baseUrl(baseUrl)
            addConverterFactory(GsonConverterFactory.create())
            client(buildClient(enableNetworkLogging))
            build()
        }
    }

    private fun buildClient(
        enableNetworkLogging: Boolean,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(NETWORK_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT.toLong(), TimeUnit.SECONDS)

        if (enableNetworkLogging) {
            val httpInterceptor = HttpLoggingInterceptor()
            httpInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.interceptors().add(httpInterceptor)
            builder.interceptors().add(PlutoInterceptor())
        }

        return builder.build()
    }
}
