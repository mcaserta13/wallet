package com.mcaserta.neontest.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mcaserta.neontest.R
import com.mcaserta.neontest.databinding.ActivityNavigationBinding
import kotlinx.android.synthetic.main.activity_navigation.*

open class NavigationActivity: AppCompatActivity() {
    private lateinit var navBinding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navBinding = DataBindingUtil.setContentView(this, R.layout.activity_navigation)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun setBarTitle(title: String) {
        navBinding.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)

        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}