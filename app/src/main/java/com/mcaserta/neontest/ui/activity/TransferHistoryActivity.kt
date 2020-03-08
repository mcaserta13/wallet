package com.mcaserta.neontest.ui.activity

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mcaserta.neontest.R
import com.mcaserta.neontest.databinding.ActivityTransferHistoryBinding
import com.mcaserta.neontest.ui.adapter.TransactionHistoryAdapter
import com.mcaserta.neontest.ui.adapter.TransferHistoryChartAdapter
import com.mcaserta.neontest.ui.adapter.TransferHistoryChartBackgroundAdapter
import com.mcaserta.neontest.utils.SharedPreferencesUtil
import com.mcaserta.neontest.viewmodel.TransferHistoryViewModel
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.activity_transfer_history.*
import java.util.*


class TransferHistoryActivity : NavigationActivity(), Observer {

    private lateinit var binding: ActivityTransferHistoryBinding
    var viewModel = TransferHistoryViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_transfer_history, activity_container, true)
        binding.viewModel = viewModel
        viewModel.addObserver(this)

        val itemDecoration = DividerItemDecoration(this, LinearLayout.VERTICAL)
        itemDecoration.setDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorActionButton)))
        rvTransferHistory.addItemDecoration(itemDecoration)

        rvTransferHistory.layoutManager = LinearLayoutManager(this)
        rvTransferChart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        setupRvChartBg()
        // Titulo da tela
        setBarTitle(getString(R.string.transaction_history))
        getTransferList()
    }

    private fun setupRvChartBg() {
        val itemDecoration = DividerItemDecoration(this, LinearLayout.VERTICAL)
        itemDecoration.setDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorGradientEnd)))
        rvChartBackground.layoutManager = LinearLayoutManager(this)
        rvChartBackground.addItemDecoration(itemDecoration)
    }

    private fun getTransferList() {
        viewModel.getTransferHistory()
    }

    override fun update(o: Observable?, arg: Any?) {
        if (arg != null) {
            when (arg.toString()) {
                TransferHistoryViewModel.TRANSFER_LIST_FETCHED -> {
                    rvTransferHistory.adapter = TransactionHistoryAdapter(viewModel.transferList, this)

                    // Popular o gráfico
                    rvTransferChart.adapter = TransferHistoryChartAdapter(viewModel.fetchChartDataList(), this)

                    rvChartBackground.adapter = TransferHistoryChartBackgroundAdapter(this)
                }
            }
        }
    }
}
