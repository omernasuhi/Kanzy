package com.kanzy.music.features.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import coil.load
import com.kanzy.domain.dto.SearchMusicDto
import com.kanzy.domain.room.db.FavMusicDB
import com.kanzy.music.R
import com.kanzy.music.base.BaseSheetBottomDialog
import com.kanzy.music.databinding.DialogBottomBinding


class InfoBottomSheet : BaseSheetBottomDialog<DialogBottomBinding>() {

    // override fun getTheme(): Int = R.style.MessageDialogTheme

    var searchMusisDto: SearchMusicDto? = null

    private val dialogTitle: String by lazy {
        arguments?.getString(INFO_BOTTOM_KEY).orEmpty()
    }

    override fun createBinding(): DialogBottomBinding {
        return DialogBottomBinding.inflate(layoutInflater)
    }

    override fun onViewReady(bundle: Bundle?) {
        initView()
        setupFullHeight()
        isCanceledOnTouchOutside = true
    }

    override fun onViewListener() {
        with(binding) {
            linearLayoutClose.setOnClickListener {
                closeDialog()
            }

            linearLayoutLike.setOnClickListener {
//                val favMusicDatabase = FavMusicDB.getFavMusicsDatabase(requireContext())
//                val favMusicItem = FavMusicsEntity(searchMusisDto?.title!!,searchMusisDto?.coverUrl!!,
//                    searchMusisDto?.videoId?.toInt()!!,searchMusisDto?.youtubeLink!!,searchMusisDto?.thumbnail!!)
//                favMusicDatabase?.favMusicDao()?.insert(favMusicItem)
            }


        }
    }

    private fun initView() {
        with(binding) {
            imageViewThumbnail.load(searchMusisDto?.thumbnail)
            textViewTitle.text = ""
        }

    }


    companion object {
        const val INFO_BOTTOM_KEY = "info.bottom.key"

        @JvmStatic
        fun show(fragment: Fragment, bundle: SearchMusicDto) {
            InfoBottomSheet().apply {
                searchMusisDto = bundle
                show(fragment.parentFragmentManager, InfoBottomSheet::class.java.name)
            }
        }
    }


}