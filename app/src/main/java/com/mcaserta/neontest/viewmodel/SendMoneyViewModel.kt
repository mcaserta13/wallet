package com.mcaserta.neontest.viewmodel

import android.content.Context
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.mcaserta.neontest.data.model.Contact
import com.mcaserta.neontest.utils.Utils
import java.util.*


class SendMoneyViewModel(private val context: Context): Observable() {

    companion object {
        const val MONEY_VALUE_CHANGED = "money_value_changed"
        const val CLOSE_DIALOG = "close_dialog"

        @BindingAdapter(value = ["bind:load_image", "bind:load_image_url"], requireAll = false)
        @JvmStatic
        fun loadImage(view: ImageView?, ctx: Context,  photoUrl: String) {
            view!!.setImageDrawable(Utils.getImageByName(photoUrl, ctx))
        }
    }

    var moneyValue = ObservableField<String>()
    var contact: Contact? = null

    fun onValueChanged(text: CharSequence?) {
        if (!text.isNullOrEmpty()) {
            moneyValue.set(Utils.formatMoney(text.toString(), context))
            setChanged()
            notifyObservers(MONEY_VALUE_CHANGED)
        }
    }

    fun sendMoney() {
    }

    fun closeDialog() {
        setChanged()
        notifyObservers(CLOSE_DIALOG)
    }
}