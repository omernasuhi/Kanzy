package com.kanzy.music.features.search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kanzy.domain.dto.SearchMusicDto
import com.kanzy.music.R
import com.kanzy.music.base.adapter.BaseViewHolder
import com.kanzy.music.base.adapter.toBinding
import com.kanzy.music.databinding.ItemMusicBinding
import com.kanzy.music.databinding.ItemTitleBinding
import com.kanzy.music.extension.cast

/*class FavoriteChipAdapter : RecyclerView.Adapter<FavoriteChipAdapter.FavoriteChipViewHolder>() {

    var items: List<FavoriteChipDto> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClicked: (FavoriteChipDto) -> Unit = {}

    inner class FavoriteChipViewHolder(binding: ItemChipFavorBinding) :
        BindingViewHolder<ItemChipFavorBinding, FavoriteChipDto>(binding) {

        override fun bindItem(item: FavoriteChipDto) {
            binding.rootPopularChip.isSelected = item.isSelected
            binding.tvChipName.setDrawableColor(item.isSelected)
            binding.tvChipName.text = item.chipName

            binding.rootPopularChip.setOnClickListener {
                onClicked(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteChipViewHolder {
        return FavoriteChipViewHolder(parent.toBinding())
    }

    override fun onBindViewHolder(holder: FavoriteChipViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


}*/


/*val renamedFavor = MutableLiveData<Boolean>()

fun renameFavorList(id: Int, name: String) = launchOn {
    call(renameFavorList.invoke(RenameFavorList.Param(id, name)),
        success = {
            renamedFavor.value = true
        }, error = {
            renamedFavor.value = false
        }
    )
}*/

/*private val chipAdapter by lazy { FavoriteChipAdapter() }
binding.rvChips.adapter = chipAdapter
chipAdapter.onClicked = {
    viewModel.selectedChipId = it.chipId
    viewModel.getFavoriteChips(it.chipId)
    viewModel.getFavoriteItems(it.chipId)
}
binding.rvFavoriteItem.setHasFixedSize(true)
val divider = requireContext().drawable(R.drawable.divider_vertical_with_margin)
divider?.let { binding.rvFavoriteItem.addDividerDrawable(it) }
binding.rvFavoriteItem.adapter = itemAdapter*/

sealed class SearchItems {
    data class Header(val title: String) : SearchItems()
    data class Content(val music: SearchMusicDto) : SearchItems()
}

fun SearchMusicDto.toSearchItemsContent() = SearchItems.Content(
    music = this
)

fun List<SearchMusicDto>.toSearchItemsContentList() = map { it.toSearchItemsContent() }

class SearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<SearchItems> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class HeaderViewHolder(binding: ItemTitleBinding) :
        BaseViewHolder<ItemTitleBinding, SearchItems.Header>(binding) {

        override fun bindItem(item: SearchItems.Header) {
            binding.tvTitle.text = item.title
            binding.rootTitle.setOnClickListener { }
        }
    }

    inner class ContentViewHolder(binding: ItemMusicBinding) :
        BaseViewHolder<ItemMusicBinding, SearchItems.Content>(binding) {

        override fun bindItem(item: SearchItems.Content) {
            binding.tvSongName.text = item.music.title
            binding.ivCover.load(item.music.coverUrl)
            binding.ivMore.setOnClickListener { }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_title -> {
                HeaderViewHolder(parent.toBinding())
            }
            R.layout.item_music -> {
                ContentViewHolder(parent.toBinding())
            }
            else -> throw IllegalStateException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is SearchItems.Header -> (holder.cast<HeaderViewHolder>()).bindItem(item)
            is SearchItems.Content -> (holder.cast<ContentViewHolder>()).bindItem(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SearchItems.Header -> R.layout.item_title
            is SearchItems.Content -> R.layout.item_music
        }
    }
}