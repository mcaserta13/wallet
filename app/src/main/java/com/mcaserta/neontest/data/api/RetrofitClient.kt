package com.mcaserta.neontest.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Configurações do Retrofit para chamadas REST
 */
class RetrofitClient {
    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://de6a863a-a5b1-4284-9612-5eaf0f9ec644.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getInstance(): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
}
