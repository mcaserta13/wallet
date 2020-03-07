package com.mcaserta.neontest.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mcaserta.neontest.R
import com.mcaserta.neontest.data.model.Contact
import com.mcaserta.neontest.databinding.ContactItemBinding
import com.mcaserta.neontest.ui.ContactListActivity


class ContactListAdapter(private val items: List<Contact>, val context: Context) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: ContactItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.contact_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Contact) {
            binding.tvContactName.text = item.name
            binding.tvContactPhone.text = item.phone
            binding.imgProfile.setImageDrawable(setImage(item))
            binding.clContactItemLayout.setOnClickListener {
                (context as ContactListActivity).showDialog(item)
            }
        }

        private fun setImage(item: Contact): Drawable {
            return Drawable.createFromStream(context.assets.open("img/" + item.photoUrl), null)
        }
    }
}