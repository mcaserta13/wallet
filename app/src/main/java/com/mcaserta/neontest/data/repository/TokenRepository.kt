package com.mcaserta.neontest.data.repository

import com.mcaserta.neontest.data.api.RetrofitClient
import com.mcaserta.neontest.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repositório para chamadas de APIs relacionadas a Token
 */
class TokenRepository {
    companion object {
        private var instance: TokenRepository? = null
        fun getInstance() = instance ?: TokenRepository().also {
            instance = it
        }
    }

    fun generateToken(user: User, onResult: (error: Boolean, response: String?, errorMessage: String?) -> Unit) {
        RetrofitClient.getInstance().generateToken(user.name, user.email).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                onResult(true, null, t.message)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                onResult(false, response.body(), null)
            }
        })
    }
}