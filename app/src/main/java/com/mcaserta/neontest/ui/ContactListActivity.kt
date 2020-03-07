package com.mcaserta.neontest.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mcaserta.neontest.R
import com.mcaserta.neontest.data.model.Contact
import com.mcaserta.neontest.databinding.ActivityContactListBinding
import com.mcaserta.neontest.databinding.SendMoneyDialogBinding
import com.mcaserta.neontest.ui.adapter.ContactListAdapter
import com.mcaserta.neontest.viewmodel.ContactViewModel
import com.mcaserta.neontest.viewmodel.SendMoneyViewModel
import kotlinx.android.synthetic.main.activity_contact_list.*
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.send_money_dialog.*
import java.util.*


class ContactListActivity : NavigationActivity(), Observer {

    private lateinit var binding: ActivityContactListBinding
    private lateinit var dialogBinding: SendMoneyDialogBinding

    private lateinit var alertDialog: AlertDialog

    var viewModel = ContactViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_contact_list, activity_container, true)
        binding.viewModel = viewModel
        viewModel.addObserver(this)

        val itemDecoration = DividerItemDecoration(this, LinearLayout.VERTICAL)
        itemDecoration.setDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorActionButton)))
        rvContactList.addItemDecoration(itemDecoration)

        rvContactList.layoutManager = LinearLayoutManager(this)
        // Titulo da tela
        setBarTitle(getString(R.string.send_money))

        // Buscar lista de contatos
        viewModel.getContactList()
    }

    override fun update(o: Observable?, arg: Any?) {
        if (arg != null) {
            when (arg.toString()) {
                ContactViewModel.SHOW_ERROR -> null
                ContactViewModel.CONTACT_LIST_UPDATED -> rvContactList.adapter = ContactListAdapter(viewModel.contactList.value!!, this)
                SendMoneyViewModel.MONEY_VALUE_CHANGED -> {  dialogBinding.edtMoney.setSelection(dialogBinding.edtMoney.length()) }
                SendMoneyViewModel.CLOSE_DIALOG -> alertDialog.dismiss()
            }
        }
    }

    fun showDialog(item: Contact) {
        val sendMoneyVM = SendMoneyViewModel(this)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBinding = DataBindingUtil.inflate(layoutInflater, R.layout.send_money_dialog, null, false)
        dialogBinding.viewModel = sendMoneyVM
        dialogBinding.ctx = this

        sendMoneyVM.contact = item
        dialogBuilder.setView(dialogBinding.root)
        sendMoneyVM.addObserver(this)

        // Abrir teclado após o Databinding realizar o inflate
        dialogBinding.edtMoney.viewTreeObserver.addOnGlobalLayoutListener {
            dialogBinding.edtMoney.requestFocus()
            val imm =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(dialogBinding.edtMoney, InputMethodManager.SHOW_IMPLICIT)
        }

        alertDialog = dialogBuilder.create()
        alertDialog.window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        alertDialog.show()

    }
}
