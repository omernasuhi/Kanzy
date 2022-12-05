package com.kanzy.music.features.library

import android.os.Bundle
import com.kanzy.domain.room.db.FavMusicDB
import com.kanzy.music.base.BaseFragment
import com.kanzy.music.databinding.FragmentLibraryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LibraryFragment : BaseFragment<FragmentLibraryBinding>() {

    companion object {
        @JvmStatic
        fun newInstance() = LibraryFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override fun createBinding(): FragmentLibraryBinding {
        return FragmentLibraryBinding.inflate(layoutInflater)
    }

    override fun onViewReady(bundle: Bundle?) {

//        val favMusicDatabase = FavMusicDB.getFavMusicsDatabase(requireContext())
//
//
//        var result = ""
//
//        val students: ArrayList<FavMusicsEntity> =
//            favMusicDatabase?.favMusicDao()?.getAllFavMusics() as ArrayList<FavMusicsEntity>
//
//        students.forEach {
//
//            result += it.title + "->" + it.youtubeLink + "\n"
//        }
//
//        binding.textView.text = result

    }
}