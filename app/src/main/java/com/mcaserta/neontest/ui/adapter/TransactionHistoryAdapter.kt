package com.mcaserta.neontest.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mcaserta.neontest.R
import com.mcaserta.neontest.utils.Utils
import com.mcaserta.neontest.data.model.Contact
import com.mcaserta.neontest.data.model.Transfer
import com.mcaserta.neontest.databinding.TransferItemBinding

class TransactionHistoryAdapter(private val items: List<Transfer>, val context: Context) : RecyclerView.Adapter<TransactionHistoryAdapter.TransactionHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHistoryViewHolder {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: TransferItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.transfer_item, parent, false)
        return TransactionHistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TransactionHistoryViewHolder, position: Int) = holder.bind(items[position])

    inner class TransactionHistoryViewHolder(private val binding: TransferItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Transfer) {
            binding.tvContactName.text = item.contact!!.name
            binding.tvContactPhone.text = item.contact!!.phone
            binding.tvAmount.text = "R$ " + Utils.formatMoney(item.amount.toString(), context)
            binding.imgProfile.setImageDrawable(setImage(item.contact!!))
        }

        private fun setImage(item: Contact): Drawable {
            return Drawable.createFromStream(context.assets.open("img/" + item.photoUrl), null)
        }
    }
}