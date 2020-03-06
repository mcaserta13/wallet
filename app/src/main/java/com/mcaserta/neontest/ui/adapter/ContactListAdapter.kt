package com.mcaserta.neontest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mcaserta.neontest.data.model.Contact
import com.mcaserta.neontest.databinding.ContactItemBinding

class ContactListAdapter(private val items: List<Contact>, val context: Context) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContactItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Contact) {
            binding.tvContactName.text = item.name
            binding.tvContactPhone.text = item.phone
            binding.imgProfile.setImageResource(getRandomImage())
        }

        private fun getRandomImage(): Int {
            val imageList = arrayOf("amy", "belchior", "belo", "bob", "faustinho", "joey", "mollie", "neto", "rochelle", "rita")
            return context.resources.getIdentifier(imageList.random() , "drawable", context.packageName)
        }
    }
}