package com.rilodev.lumutapps.core.utils.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.rilodev.lumutapps.core.utils.CustomDialog
import com.rilodev.lumutapps.core.utils.ViewModelFactory
import com.rilodev.lumutapps.pages.MainViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<VB: ViewBinding, VM : ViewModel>(
    private val viewModelClass: KClass<VM>
) : AppCompatActivity() {
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    protected val viewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    private var loadingDialog: Dialog? = null

    @Suppress("UNCHECKED_CAST")
    private val binding: VB get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    abstract fun VB.initUI()

    abstract fun VB.initEvent()

    abstract fun VB.initObserve()

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }

    protected fun showLoading(cancelable: Boolean = true, message: String? = null) {
        if (loadingDialog == null) {
            loadingDialog = CustomDialog.loading(this, cancelable, message)
            loadingDialog?.show()
        } else {
            if (loadingDialog?.isShowing != true)
                loadingDialog?.show()
        }
    }

    protected fun dismissLoading() {
        loadingDialog?.dismiss()
    }
}