package com.mcaserta.neontest.data.api

import com.mcaserta.neontest.data.model.Transfer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Interface de chamadas de APIs
 */
interface ApiService {

    @GET("generate-token")
    fun generateToken(@Query("name") name: String, @Query("email") email: String): Call<String>

    @POST("send-money")
    fun sendMoney(@Body transfer: Transfer): Call<Boolean>

    @GET("transfers")
    fun getTransfers(@Query("token") token: String): Call<ArrayList<Transfer>>
}