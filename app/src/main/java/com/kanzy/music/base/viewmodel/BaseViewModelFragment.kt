package com.kanzy.music.base.viewmodel

import androidx.viewbinding.ViewBinding
import com.kanzy.music.base.BaseFragment
import com.kanzy.music.extension.observe
import com.kanzy.music.extension.observeState

abstract class BaseViewModelFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFragment<VB>() {

    abstract val viewModel: VM

    override fun onObserveState() {
        super.onObserveState()
        observeLoadingState()
        observeErrorMessage()
    }

    private fun observeLoadingState() {
        observeState(viewModel.isLoading) { state ->
            state?.let {
                if (it) {
                    showLoading()
                } else {
                    hideLoading()
                }
            }
        }
    }

    private fun observeErrorMessage() {
        observe(viewModel.error) { error ->

        }
    }


}