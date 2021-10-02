package com.kanzy.music.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.kanzy.music.component.LoadingDialog

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var loadingDialog: LoadingDialog? = null

    lateinit var binding: VB

    abstract fun createBinding(): VB

    abstract fun onViewReady(bundle: Bundle?)

    open fun onViewListener() {}

    open fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        loadingDialog?.show()
    }

    open fun hideLoading() {
        loadingDialog?.dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (::binding.isInitialized.not()) {
            binding = createBinding()
            setContentView(binding.root)
        }
        onViewReady(savedInstanceState)
        onViewListener()
    }

}