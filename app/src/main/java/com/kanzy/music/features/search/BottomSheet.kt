package com.kanzy.music.features.search

import android.os.Bundle
import com.kanzy.music.base.BaseBottomDialog
import com.kanzy.music.databinding.FragmentHomeBinding
import com.kanzy.music.databinding.DialogBottomBinding

class BottomSheet: BaseBottomDialog<DialogBottomBinding>() {

    override fun createBinding(): DialogBottomBinding {
        return DialogBottomBinding.inflate(layoutInflater)
    }

    override fun onViewReady(bundle: Bundle?) {
    }
}