package com.social.troops.data.remote

import android.text.TextUtils
import com.app.marketplace.sellBuyDevice.data.remote.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.social.troops.AppManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {

        var apiService: ApiService = Retrofit.Builder().baseUrl(AppManager.REMOTE_DATA_BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(ApiService::class.java)

        var authorizedApiService: ApiService = Retrofit.Builder().baseUrl(AppManager.REMOTE_DATA_BASE_URL)
                .client(getAuthorizedOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(ApiService::class.java)

        //        private var okHttpClient: OkHttpClient? = null
        private var httpLoggingInterceptor: HttpLoggingInterceptor? = null

//        fun getApiService(): ApiService? {
//            if (apiService == null) {
//                val retrofit = Retrofit.Builder().baseUrl(AppManager.REMOTE_DATA_BASE_URL)
//                    .client(getOkHttpClient())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                    .build()
//                apiService = retrofit.create(ApiService::class.java)
//            }
//            return apiService
//        }

        fun getOkHttpClient(): OkHttpClient {
            if (AppManager.IS_LOGGING_ENABLED) {
                return OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val request = chain.request().newBuilder()
                                    .addHeader("Accept", "application/json")
//                            .addHeader("Content-Type", "application/json")
//                            .addHeader("Device-Type", "android")
//                            .addHeader("Version-Code", "1")
                                    .build()
                            chain.proceed(request)
                        }
                        .addInterceptor(getHttpLoggingInterceptor())
                        .readTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .build()
            } else {
                return OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val request = chain.request().newBuilder()
                                    .addHeader("Accept", "application/json")
//                            .addHeader("Content-Type", "application/json")
//                            .addHeader("Device-Type", "android")
//                            .addHeader("Version-Code", "1")
                                    .build()
                            chain.proceed(request)
                        }
                        .readTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .build()
            }
        }

        fun getAuthorizedOkHttpClient(): OkHttpClient {
            if (AppManager.IS_LOGGING_ENABLED) {
                return OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val request = chain.request().newBuilder()
                                    .addHeader("Accept", "application/json")
                                    .addHeader("Authorization", getAuthorizationKey())
                                    .build()
                            chain.proceed(request)
                        }
                        .addInterceptor(getHttpLoggingInterceptor())
                        .readTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .build()
            } else {
                return OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val request = chain.request().newBuilder()
                                    .addHeader("Accept", "application/json")
                                    .addHeader("Authorization", getAuthorizationKey())
                                    .build()
                            chain.proceed(request)
                        }
                        .readTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .build()
            }
        }

        private fun getAuthorizationKey(): String {
            AppManager.authSalt?.let { salt ->
                AppManager.getUser()?.let {
                    return salt.plus(it.token)
                }
            }
            if(!TextUtils.isEmpty(AppManager.preferences?.getString("auth_token",""))){
                return  AppManager.authSalt+AppManager.preferences?.getString("auth_token","")

            }


            return ""
        }

        fun getHttpLoggingInterceptor(): Interceptor {
            if (httpLoggingInterceptor == null) {
                httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor!!.level = HttpLoggingInterceptor.Level.BODY
            }
            return httpLoggingInterceptor as HttpLoggingInterceptor
        }
    }
}