package com.mcaserta.neontest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.mcaserta.neontest.R
import com.mcaserta.neontest.data.model.User
import com.mcaserta.neontest.databinding.ActivityHomeBinding
import com.mcaserta.neontest.ui.config.gotoContactList
import com.mcaserta.neontest.ui.config.gotoTransferHistory
import com.mcaserta.neontest.utils.SharedPreferencesUtil
import com.mcaserta.neontest.utils.Utils
import com.mcaserta.neontest.viewmodel.UserViewModel
import java.util.*

class HomeActivity : AppCompatActivity(), Observer {

    private lateinit var binding: ActivityHomeBinding
    private var viewModel: UserViewModel = Utils.getAuthUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
        viewModel.addObserver(this)

        // Obter o token
        viewModel.getToken()

        // Observables da chamada de token
        setObservables()
    }

    private fun setObservables() {
        // Sucesso
        viewModel.response.observe(this, androidx.lifecycle.Observer {
            SharedPreferencesUtil(this).save(SharedPreferencesUtil.SHARED_TOKEN, it)
        })
    }

    // Update do observer - ViewModel
    override fun update(o: Observable?, arg: Any?) {
        if (arg != null) {
            when (arg.toString()) {
                UserViewModel.SHOW_CONTACT_LIST -> gotoContactList()
                UserViewModel.SHOW_TRANSFER_HISTORY -> gotoTransferHistory()
            }
        }
    }


}
