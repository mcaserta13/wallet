package com.mcaserta.neontest.viewmodel

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.mcaserta.neontest.R
import com.mcaserta.neontest.data.model.Contact
import com.mcaserta.neontest.data.model.Transfer
import com.mcaserta.neontest.data.repository.TransferRepository
import com.mcaserta.neontest.utils.SharedPreferencesUtil
import com.mcaserta.neontest.utils.Utils
import com.mcaserta.neontest.utils.keepOnlyNumbers
import java.util.*

class SendMoneyViewModel(private val context: Context) : Observable() {

    companion object {
        const val MONEY_VALUE_CHANGED = "money_value_changed"
        const val CLOSE_DIALOG = "close_dialog"
        const val SHOW_SUCCESS_ANIM = "show_success_anim"
        const val SHOW_ERROR_ANIM = "show_error_anim"
        const val LOADING_CALLED = "loading_called"

        @BindingAdapter(value = ["bind:load_image", "bind:load_image_url"], requireAll = false)
        @JvmStatic
        fun loadImage(view: ImageView?, ctx: Context, photoUrl: String) {
            view!!.setImageDrawable(Utils.getImageByName(photoUrl, ctx))
        }
    }

    var moneyValue = ObservableField<String>()
    var contact: Contact? = null
    var isValidValue = ObservableField<Boolean>(false)
    var isLoading = ObservableField<Boolean>(false)
    var showAnimation = ObservableField<Boolean>(false)
    var repository: TransferRepository = TransferRepository()
    var message = ObservableField<String>()
    val success = ObservableField<Boolean>(false)

    fun onValueChanged(text: CharSequence?) {
        if (!text.isNullOrEmpty()) {

            if (text.toString().keepOnlyNumbers().toIntOrNull() != null) {
                isValidValue.set(text.toString().keepOnlyNumbers().toInt() > 0)
            }

            moneyValue.set(Utils.formatMoney(text.toString(), context))
            setChanged()
            notifyObservers(MONEY_VALUE_CHANGED)
        }
    }

    fun sendMoney() {
        val transfer = Transfer(
            0,
            contact!!.id,
            SharedPreferencesUtil((context as Activity)).get(SharedPreferencesUtil.SHARED_TOKEN)!!,
            moneyValue.get().toString().keepOnlyNumbers().toDouble()
        )

        isLoading.set(true)
        setChanged()
        notifyObservers(LOADING_CALLED)
        repository.sendMoney(transfer) { error, response, errorMessage ->
            isLoading.set(false)
            showAnimation.set(true)
            setChanged()

            if (error) {
                success.set(false)
                message.set(context.getString(R.string.error_msg))
                notifyObservers(SHOW_ERROR_ANIM)
            } else {
                success.set(true)
                message.set(context.getString(R.string.success_msg))
                notifyObservers(SHOW_SUCCESS_ANIM)
            }
        }
    }

    fun closeDialog() {
        setChanged()
        notifyObservers(CLOSE_DIALOG)
    }
}