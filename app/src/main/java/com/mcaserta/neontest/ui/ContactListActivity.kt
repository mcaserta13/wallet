package com.mcaserta.neontest.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.mcaserta.neontest.R
import com.mcaserta.neontest.databinding.ActivityContactListBinding
import com.mcaserta.neontest.ui.adapter.ContactListAdapter
import com.mcaserta.neontest.viewmodel.ContactViewModel
import kotlinx.android.synthetic.main.activity_contact_list.*
import kotlinx.android.synthetic.main.activity_navigation.*
import java.util.*

class ContactListActivity : NavigationActivity(), Observer {

    private lateinit var binding: ActivityContactListBinding
    var viewModel = ContactViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_contact_list, activity_container, true)
        binding.viewModel = viewModel
        viewModel.addObserver(this)

        val itemDecoration = DividerItemDecoration(this, LinearLayout.VERTICAL)
        itemDecoration.setDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorActionButton)))
        rvContactList.addItemDecoration(itemDecoration)
        // Titulo da tela
        setBarTitle(getString(R.string.send_money))

        // Buscar lista de contatos
        viewModel.getContactList()

    }

    override fun update(o: Observable?, arg: Any?) {
        if (arg != null) {
            when (arg.toString()) {
                ContactViewModel.SHOW_ERROR -> null // TODO Exibir tela de erro
                ContactViewModel.CONTACT_LIST_UPDATED -> rvContactList.adapter = ContactListAdapter(viewModel.contactList.value!!, this)
            }
        }
    }
}
