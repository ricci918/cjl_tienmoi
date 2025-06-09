package com.tienmoi.vayonline.nhanh.model.utils

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object TienHttpUtil {
    private const val CONNECT_TIME_OUT = 60
    private var mRetrofit: Retrofit? = null
    const val BASE_URL = "https://www.tienmoivayonline.com"
    private const val LANGUAGE = "vi_VN"
    fun getService(): Retrofit {
        return if (mRetrofit == null) {

            val okHttpBuilder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(interceptor)
            val okHttpClient = okHttpBuilder
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        } else {
            mRetrofit!!
        }
    }

    private var interceptor = Interceptor { chain ->
        val builder = chain.request().newBuilder()
            .addHeader("Accept-Language", LANGUAGE)
        val request = builder.build()

        return@Interceptor chain.proceed(request)
    }

}