package com.mcaserta.neontest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mcaserta.neontest.R
import com.mcaserta.neontest.data.model.User
import com.mcaserta.neontest.databinding.ActivityHomeBinding
import com.mcaserta.neontest.viewmodel.UserViewModel
import java.util.*

class HomeActivity : AppCompatActivity(), Observer {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: UserViewModel

    init {
        getAuthUser()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
        viewModel.addObserver(this)
    }

    // Update do observer - ViewModel
    override fun update(o: Observable?, arg: Any?) {
        if (arg != null) {
            when (arg.toString()) {
                UserViewModel.SHOW_CONTACT_LIST -> null // TODO enviar para a tela de contatos
                UserViewModel.SHOW_TRANSFER_HISTORY -> null // TODO enviar para a tela de histórico de transações
            }
        }
    }

    // Obter o usuário autenticado (mock)
    private fun getAuthUser() {
        viewModel = UserViewModel(User("Maurício Caserta", "mauriciocaserta@gmail.com"))
    }
}
