package com.mcaserta.neontest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mcaserta.neontest.R
import com.mcaserta.neontest.databinding.TransferChartBgItemBinding

class TransferHistoryChartBackgroundAdapter(private val context: Context) : RecyclerView.Adapter<TransferHistoryChartBackgroundAdapter.TransactionHistoryChartBgViewHolder>() {
    private var height = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHistoryChartBgViewHolder {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: TransferChartBgItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.transfer_chart_bg_item, parent, false)

        return TransactionHistoryChartBgViewHolder(binding)
    }

    override fun getItemCount(): Int = 20

    override fun onBindViewHolder(holder: TransactionHistoryChartBgViewHolder, position: Int) {}

    inner class TransactionHistoryChartBgViewHolder(private val binding: TransferChartBgItemBinding) : RecyclerView.ViewHolder(binding.root)
}