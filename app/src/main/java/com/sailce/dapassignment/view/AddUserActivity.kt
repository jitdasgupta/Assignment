package com.sailce.dapassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.sailce.dapassignment.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.sailce.dapassignment.db.UserEntity
import com.sailce.dapassignment.viewmodel.CommonViewModel
import kotlinx.android.synthetic.main.activity_add_user.*


class AddUserActivity : AppCompatActivity() {

    lateinit var viewModel: CommonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        viewModel = ViewModelProviders.of(this).get(CommonViewModel::class.java)
        viewModel.getInsertObservers().observe(this, Observer {
           Toast.makeText(this@AddUserActivity, it, Toast.LENGTH_SHORT)
                .show()
            et_name.setText("")
            et_address.setText("")
            et_email.setText("")
            et_phone.setText("")
        })
        viewModel.getErrorValidation().observe(this, Observer {
            Toast.makeText(this@AddUserActivity, it, Toast.LENGTH_SHORT)
                .show()
        })
        saveButton.setOnClickListener {
            val name  = et_name.text.toString()
            val address = et_address.text.toString()
            val email  = et_email.text.toString()
            val phone = et_phone.text.toString()
            val user = UserEntity(0, name,address, email, phone)
            viewModel.insertUserInfo(user)
        }
    }
}