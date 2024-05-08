package com.rilodev.lumutapps.core.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.rilodev.lumutapps.databinding.LoadingDialogBinding

object CustomDialog {
    fun loading(
        context: Context,
        cancelable: Boolean = true,
        loadingTitle: String? = null
    ): Dialog {
        val dialog = Dialog(context)
        val binding = LoadingDialogBinding.inflate(LayoutInflater.from(context))
        if (!loadingTitle.isNullOrEmpty())
            binding.loadingTitle.text = loadingTitle
        dialog.setContentView(binding.root)
        dialog.setCancelable(cancelable)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}
