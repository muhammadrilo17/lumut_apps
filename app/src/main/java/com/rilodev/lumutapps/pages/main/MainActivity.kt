package com.rilodev.lumutapps.pages.main

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rilodev.lumutapps.core.data.Resource
import com.rilodev.lumutapps.core.utils.Constants
import com.rilodev.lumutapps.core.utils.adapter.TodoAdapter
import com.rilodev.lumutapps.core.utils.view.BaseActivity
import com.rilodev.lumutapps.databinding.ActivityMainBinding
import com.rilodev.lumutapps.pages.MainViewModel
import com.rilodev.lumutapps.pages.detail.DetailActivity
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private lateinit var todoAdapter: TodoAdapter

    override fun ActivityMainBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.todos.collect {
                when (it) {
                    is Resource.Loading -> {
                        showLoading(false)
                    }

                    is Resource.Success -> {
                        dismissLoading()
                        todoAdapter.submitList(it.data)
                    }

                    is Resource.Error -> {
                        dismissLoading()
                        Log.d("RESULT ERROR", it.message.toString())
                        Toast.makeText(this@MainActivity, it.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun ActivityMainBinding.initEvent() {
        todoAdapter.onItemClick = { id ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(Constants.EXTRA_ID, id)
            startActivity(intent)
        }
    }

    override fun ActivityMainBinding.initUI() {
        todoAdapter = TodoAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = todoAdapter

        viewModel.todos()
    }
}