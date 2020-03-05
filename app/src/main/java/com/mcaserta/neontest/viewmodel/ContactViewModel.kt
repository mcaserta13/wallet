package com.mcaserta.neontest.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mcaserta.neontest.data.model.Contact
import java.util.*

class ContactViewModel : Observable() {
    companion object {
        const val SHOW_ERROR = "show_error"
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

        // Mock para a lista de contatos
        for (i in 1..20) {
            val phone = (11111111..99999999).random()
            val contact = Contact(
                i,
                nameList.random() + " " + lastNameList.random(),
                "119$phone",
                "photo"
            )

            auxList.add(contact)
        }

        contactList.postValue(auxList)
    }
}