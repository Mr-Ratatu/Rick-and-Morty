package com.rick.and.morti.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rick.and.morti.databinding.ItemStateBinding

class CharacterListState(private val retry: () -> Unit) :
    LoadStateAdapter<CharacterListState.StateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        StateViewHolder(
            ItemStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), retry
        )

    override fun onBindViewHolder(holder: StateViewHolder, loadState: LoadState) {
        holder.binding.apply {
            loading.isVisible = loadState is LoadState.Loading
            retry.isVisible = loadState is LoadState.Error
        }
    }

    class StateViewHolder(
        val binding: ItemStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retry.setOnClickListener {
                retry.invoke()
            }
        }

    }

}