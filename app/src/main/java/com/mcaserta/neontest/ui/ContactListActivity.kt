package com.mcaserta.neontest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mcaserta.neontest.R
import com.mcaserta.neontest.databinding.ActivityContactListBinding
import com.mcaserta.neontest.viewmodel.ContactViewModel
import java.util.*

class ContactListActivity : AppCompatActivity(), Observer {

    private lateinit var binding: ActivityContactListBinding
    var viewModel = ContactViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_list)
        binding.viewModel = viewModel
        viewModel.addObserver(this)

        // Buscar lista de contatos
        viewModel.getContactList()
    }

    override fun update(o: Observable?, arg: Any?) {
        if (arg != null) {
            when (arg.toString()) {
                ContactViewModel.SHOW_ERROR -> null // TODO Exibir tela de erro
            }
        }
    }
}
