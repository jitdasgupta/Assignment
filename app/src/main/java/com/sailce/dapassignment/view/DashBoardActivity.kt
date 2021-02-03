package com.sailce.dapassignment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sailce.dapassignment.R
import com.sailce.dapassignment.databinding.ActivityDashBoardBinding
import com.sailce.dapassignment.db.UserEntity
import com.sailce.dapassignment.view.utill.DashBoardHandler
import com.sailce.dapassignment.viewmodel.CommonViewModel
import kotlinx.android.synthetic.main.activity_add_user.*
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity(),DashBoardHandler {

    lateinit var viewModel: CommonViewModel
    lateinit var binding:ActivityDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dash_board)
        viewModel = ViewModelProviders.of(this).get(CommonViewModel::class.java)
        binding.viewModel=viewModel
        viewModel.getinsertUser().observe(this, Observer {
            if(it){
                val intent = Intent(this, AddUserActivity::class.java)
                startActivity(intent)
            }
        })
        viewModel.getShowUser().observe(this, Observer {
            if(it) {
                val intent = Intent(this, UsersListActivity::class.java)
                startActivity(intent)
            }
        })
    }
    override fun onUserListingScreenOpen() {
       viewModel.openUserInsert()
    }

    override fun onUserAddScreenOpen() {
        viewModel.openUserListing()
    }
}