package com.mcaserta.neontest.ui.config

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.mcaserta.neontest.ui.activity.ContactListActivity
import com.mcaserta.neontest.ui.activity.TransferHistoryActivity

/**
 * Roteador para activities e fragments
 */
fun AppCompatActivity.gotoContactList(it: Intent? = null) {
    startActivity(it ?: Intent(this, ContactListActivity::class.java))
}

fun AppCompatActivity.gotoTransferHistory(it: Intent? = null) {
    startActivity(it ?: Intent(this, TransferHistoryActivity::class.java))
}