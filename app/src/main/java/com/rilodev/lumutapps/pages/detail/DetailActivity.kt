package com.rilodev.lumutapps.pages.detail

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.rilodev.lumutapps.core.data.Resource
import com.rilodev.lumutapps.core.utils.Constants
import com.rilodev.lumutapps.core.utils.view.BaseActivity
import com.rilodev.lumutapps.databinding.ActivityDetailBinding
import com.rilodev.lumutapps.pages.MainViewModel
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class DetailActivity : BaseActivity<ActivityDetailBinding, MainViewModel>(MainViewModel::class) {
    override val bindingInflater: (LayoutInflater) -> ActivityDetailBinding
        get() = ActivityDetailBinding::inflate

    override fun ActivityDetailBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.todosById.collect {
                when (it) {
                    is Resource.Loading -> {
                        showLoading(false)
                    }

                    is Resource.Success -> {
                        dismissLoading()

                        textViewTitle.text = StringBuilder(it.data?.title.toString())
                        textViewId.text = StringBuilder("ID: " + it.data?.id.toString())
                        textViewUserId.text = StringBuilder("User ID: " + it.data?.userId.toString())
                        textViewCompleted.text = StringBuilder(if(it.data?.completed == true) "Berhasil" else "Gagal")
                    }

                    is Resource.Error -> {
                        dismissLoading()
                        Toast.makeText(this@DetailActivity, it.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun ActivityDetailBinding.initEvent() {

    }

    override fun ActivityDetailBinding.initUI() {
        val id = intent.getIntExtra(Constants.EXTRA_ID, 0)
        if (id != 0) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)

            viewModel.todosById(id)
        } else {
            Toast.makeText(this@DetailActivity, "Data Tidak Dapat Ditampilkan", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}