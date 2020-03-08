package com.mcaserta.neontest.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mcaserta.neontest.R
import com.mcaserta.neontest.data.model.TransferChartData
import com.mcaserta.neontest.databinding.TransferChartItemBinding
import com.mcaserta.neontest.utils.Utils


class TransferHistoryChartAdapter(private val items: List<TransferChartData>, val context: Context) : RecyclerView.Adapter<TransferHistoryChartAdapter.TransactionHistoryChartViewHolder>() {
    private var height = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHistoryChartViewHolder {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: TransferChartItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.transfer_chart_item, parent, false)

        return TransactionHistoryChartViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TransactionHistoryChartViewHolder, position: Int) = holder.bind(items[position])

    inner class TransactionHistoryChartViewHolder(private val binding: TransferChartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TransferChartData) {
            binding.imgProfile.setImageDrawable(setImage(item.photoUrl))
            binding.tvAmount.text = "R$ " + Utils.formatMoney(item.amount.toString(), context)

            binding.llContent.doOnPreDraw {
                var height = 0f
                if (item.amount > 0) {
                    height = itemView.height * (item.percentageOnChart.toFloat() / 100)
                }
                binding.vChartHLine.layoutParams.height = height.toInt()
                binding.vChartHLine.requestLayout()
            }
        }

        private fun setImage(photoUrl: String): Drawable {
            return Drawable.createFromStream(context.assets.open("img/$photoUrl"), null)
        }
    }
}