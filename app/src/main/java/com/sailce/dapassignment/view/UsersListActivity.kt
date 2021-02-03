package com.sailce.dapassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sailce.dapassignment.R
import com.sailce.dapassignment.view.adapter.RecyclerViewAdapter
import com.sailce.dapassignment.view.utill.NameComparatorAscending
import com.sailce.dapassignment.viewmodel.CommonViewModel
import kotlinx.android.synthetic.main.activity_users_list.*
import java.util.*
import kotlin.collections.ArrayList
import android.widget.Toast.makeText as makeText1

class UsersListActivity : AppCompatActivity() {

    lateinit var viewModel: CommonViewModel
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@UsersListActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }
        viewModel = ViewModelProviders.of(this).get(CommonViewModel::class.java)
        viewModel.getAllUsersObservers().observe(this, Observer {
            var nameComparatorAscending : NameComparatorAscending =NameComparatorAscending()
            Collections.sort(it, nameComparatorAscending)
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })
        viewModel.getAllUsers()
    }
}