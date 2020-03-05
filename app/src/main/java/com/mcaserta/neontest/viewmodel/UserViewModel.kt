package com.mcaserta.neontest.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mcaserta.neontest.data.model.User
import com.mcaserta.neontest.data.repository.TokenRepository
import java.util.Observable

class UserViewModel(userModel: User): Observable() {
    companion object {
        const val SHOW_CONTACT_LIST = "show_contact_list"
        const val SHOW_TRANSFER_HISTORY = "show_transfer_history"
    }

    var user: User = userModel
    val error = MutableLiveData<Boolean>()
    private val errorMsg = MutableLiveData<String>()
    val response = MutableLiveData<String>()

    private val repository = TokenRepository.getInstance()
 
    fun showContactList() {
        setChanged()
        notifyObservers(SHOW_CONTACT_LIST)
    }

    fun showTransferHistory() {
        setChanged()
        notifyObservers(SHOW_TRANSFER_HISTORY)
    }

    fun getToken() {
        repository.generateToken(user) { error, response, errorMessage ->
            if (error) {
                this.error.postValue(error)
                this.errorMsg.postValue(errorMessage)
            } else {
                this.response.postValue(response)
            }
        }
    }
}