package com.mcaserta.neontest.data.repository

import com.mcaserta.neontest.data.api.RetrofitClient
import com.mcaserta.neontest.data.model.Transfer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repositório para chamadas de APIs relacionadas a Transferencias
 */
class TransferRepository {
    companion object {
        private var instance: TransferRepository? = null
        fun getInstance() = instance ?: TransferRepository().also {
            instance = it
        }
    }

    fun sendMoney(transfer: Transfer, onResult: (error: Boolean, response: Boolean?, errorMessage: String?) -> Unit) {
        RetrofitClient.getInstance().sendMoney(transfer).enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                onResult(true, null, t.message)
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                onResult(false, response.body(), null)
            }
        })
    }

    fun getTransfers(token: String, onResult: (error: Boolean, response: ArrayList<Transfer>?, errorMessage: String?) -> Unit) {
        RetrofitClient.getInstance().getTransfers(token).enqueue(object : Callback<ArrayList<Transfer>> {
            override fun onFailure(call: Call<ArrayList<Transfer>>, t: Throwable) {
                onResult(true, null, t.message)
            }

            override fun onResponse(
                call: Call<ArrayList<Transfer>>,
                response: Response<ArrayList<Transfer>>
            ) {
                onResult(false, response.body(), null)
            }
        })
    }
}