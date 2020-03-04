package com.mcaserta.neontest.viewmodel

import com.mcaserta.neontest.data.model.User
import java.util.Observable

class UserViewModel(userModel: User): Observable() {
    var user: User = userModel

    companion object {
        const val SHOW_CONTACT_LIST = "show_contact_list"
        const val SHOW_TRANSFER_HISTORY = "show_transfer_history"
    }

    fun showContactList() {
        setChanged()
        notifyObservers(SHOW_CONTACT_LIST)
    }

    fun showTransferHistory() {
        setChanged()
        notifyObservers(SHOW_TRANSFER_HISTORY)
    }
}