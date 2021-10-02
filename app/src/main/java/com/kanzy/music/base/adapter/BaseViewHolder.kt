package com.kanzy.music.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

inline fun <reified V : ViewBinding> ViewGroup.toBinding(): V {
    return V::class.java.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    ).invoke(null, LayoutInflater.from(context), this, false) as V
}

interface BindModel<T> {
    fun bindItem(item: T)
}

abstract class BaseViewHolder<VB : ViewBinding, M : Any>(val binding: VB) :
    RecyclerView.ViewHolder(binding.root), BindModel<M> {

    val context: Context = binding.root.context

}

class ItemDiffCallback<T>(
    val onItemsTheSame: (T, T) -> Boolean,
    val onContentsTheSame: (T, T) -> Boolean
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = onItemsTheSame(oldItem, newItem)
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        onContentsTheSame(oldItem, newItem)
}

abstract class BListAdapter<T, VH : RecyclerView.ViewHolder>(diffUtil: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(diffUtil) {

    override fun submitList(list: MutableList<T>?) =
        super.submitList(list?.let { ArrayList(it) } ?: emptyList())

    override fun submitList(list: MutableList<T>?, commitCallback: Runnable?) =
        super.submitList(list?.let { ArrayList(it) } ?: emptyList(), commitCallback)

}