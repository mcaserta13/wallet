package com.mcaserta.neontest.viewmodel

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import com.mcaserta.neontest.R
import com.mcaserta.neontest.data.model.Transfer
import com.mcaserta.neontest.data.model.TransferChartData
import com.mcaserta.neontest.data.repository.TokenRepository
import com.mcaserta.neontest.data.repository.TransferRepository
import com.mcaserta.neontest.utils.SharedPreferencesUtil
import com.mcaserta.neontest.utils.Utils
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class TransferHistoryViewModel(private val context: Context): Observable() {
    companion object {
        const val TRANSFER_LIST_FETCHED = "transfer_list_fetched"
    }

    private val repository = TransferRepository()
    private val tokenRepository = TokenRepository()
    val transferList = ArrayList<Transfer>()
    private val transferChartDataList = ArrayList<TransferChartData>()
    val isLoading = ObservableField<Boolean>(false)
    val isError = ObservableField<Boolean>(false)

    fun getTransferHistory() {
        isLoading.set(true)

        if (SharedPreferencesUtil(context as Activity).get(SharedPreferencesUtil.SHARED_TOKEN).isNullOrEmpty()) {
            tokenRepository.generateToken(Utils.getAuthUser().user) { error, response, _ ->
                if (error) {
                    isLoading.set(false)

                    isError.set(true)
                    setChanged()
                } else {
                    SharedPreferencesUtil(context).save(SharedPreferencesUtil.SHARED_TOKEN, response!!)
                    sendRequest()
                }
            }
        } else {
            sendRequest()
        }
    }

    private fun sendRequest() {
        repository.getTransfers(SharedPreferencesUtil(context as Activity).get(SharedPreferencesUtil.SHARED_TOKEN)!!) { error, response, _ ->
            setChanged()
            isLoading.set(false)
            if (error) {
                isError.set(true)
            } else {
                transferList.addAll(response!!)
                notifyObservers(TRANSFER_LIST_FETCHED)
            }
        }
    }

    fun fetchChartDataList(): ArrayList<TransferChartData> {
        // Lista com amount somado
        for (transfer in transferList) {

            // Caso já exista o cliente na lista, apenas somar o amount
            if (transferChartDataList.find { it.clientId == transfer.clientId } != null) {
                transferChartDataList.find { it.clientId == transfer.clientId }!!.amount += transfer.amount
            } else {
                val chartData = TransferChartData()
                chartData.clientId = transfer.clientId
                chartData.photoUrl = transfer.contact!!.photoUrl
                chartData.amount = transfer.amount
                transferChartDataList.add(chartData)
            }
        }

        Collections.sort(transferChartDataList,
            Comparator { c1, c2 -> c2.amount.compareTo(c1.amount) })

        // Maior valor de transferencia, utilizado como base para porcentagem do grafico
        val highestValue = transferChartDataList[0].amount

        // Calcular porcentagem de cada item
        for (transferChartData in transferChartDataList) {
            val percent = (transferChartData.amount * 60) / highestValue

            transferChartDataList.find { it == transferChartData }!!.percentageOnChart = percent
        }

        return transferChartDataList
    }
}