package com.kanzy.music.component

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import com.kanzy.music.databinding.DialogLoadingBinding

class LoadingDialog(context: Context) : Dialog(context) {

    init {
        val binding = DialogLoadingBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        setCanceledOnTouchOutside(false)
        setCancelable(true)
        setContentView(binding.root)
    }

}