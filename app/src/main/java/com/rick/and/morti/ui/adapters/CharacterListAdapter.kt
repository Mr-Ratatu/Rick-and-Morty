package com.rick.and.morti.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rick.and.morti.common.base.DiffUtils
import com.rick.and.morti.data.model.CharacterResult
import com.rick.and.morti.databinding.ItemCharacterBinding

class CharacterListAdapter(private val listener: CharacterListener) :
    PagingDataAdapter<CharacterResult, CharacterListAdapter.ViewHolder>(DiffUtils()) {

    interface CharacterListener {
        fun onClickCharacter(result: CharacterResult?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), listener
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(val binding: ItemCharacterBinding, private val listener: CharacterListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: CharacterResult?) {
            binding.apply {
                root.setOnClickListener { listener.onClickCharacter(result) }
                items = result
                executePendingBindings()
            }
        }

    }

}