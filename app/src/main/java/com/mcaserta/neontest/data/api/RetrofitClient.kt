package com.mcaserta.neontest.data.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Configurações do Retrofit para chamadas REST
 */
class RetrofitClient {
    companion object {
        private val gson = GsonBuilder().setLenient().create()

        fun getInstance(): ApiService {
            return getRetrofitInstance().create(ApiService::class.java)
        }

        private fun getRetrofitInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            return Retrofit.Builder()
                .baseUrl("https://de6a863a-a5b1-4284-9612-5eaf0f9ec644.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
        }
    }
}
