package com.kanzy.music.base.viewmodel

import androidx.viewbinding.ViewBinding
import com.kanzy.music.base.BaseActivity

abstract class BaseViewModelActivity<VB : ViewBinding, VM : BaseViewModel> : BaseActivity<VB>() {

    abstract val viewModel: VM



}