package com.mcaserta.neontest.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.mcaserta.neontest.data.model.Contact
import java.util.*

class ContactViewModel(private val context: Context) : Observable() {
    companion object {
        const val SHOW_ERROR = "show_error"
        const val CONTACT_LIST_UPDATED = "contact_list_updated"
    }

    val contactList = MutableLiveData<ArrayList<Contact>>()

    fun getContactList() {
        val auxList = ArrayList<Contact>()

        val nameList = arrayOf(
            "João",
            "José",
            "Belchior",
            "Astolfo",
            "Bob",
            "Alex",
            "Mauricio",
            "Chico",
            "Maria",
            "Rita",
            "Fabiana",
            "Joana",
            "Elizabete",
            "Claudia",
            "Clotilde"
        )
        val lastNameList = arrayOf(
            "Silva",
            "Oliveira",
            "Costa",
            "Santos",
            "Buarque",
            "Marley",
            "Santana",
            "Vilela"
        )

        val imageList = context.assets.list("img")

        // Mock para a lista de contatos
        for (i in 1..20) {
            val phone = (11111111..99999999).random()
            val contact = Contact(
                i,
                nameList.random() + " " + lastNameList.random(),
                "(11)9$phone",
                imageList.random()
            )

            auxList.add(contact)
        }

        contactList.value = auxList
        setChanged()
        notifyObservers(CONTACT_LIST_UPDATED)
    }
}