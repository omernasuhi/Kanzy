package com.inomera.sm.ui.foodfilterrestaurants.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kanzy.domain.dto.SearchMusicDto
import com.kanzy.music.databinding.ItemMusicBinding


class SearchAdapter(
    private val searchList: List<SearchMusicDto>,
    private val onSearchItemClickListener:  SearchItemClickListener,
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchList[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    inner class ViewHolder(val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: SearchMusicDto) {
            binding.tvSongName.text = item.title
            binding.ivCover.load(item.thumbnail)
            binding.root.setOnClickListener {
                onSearchItemClickListener.musicListItemClicked(item)
            }
        }
    }
    interface SearchItemClickListener {
        fun musicListItemClicked(item: SearchMusicDto)
    }
}