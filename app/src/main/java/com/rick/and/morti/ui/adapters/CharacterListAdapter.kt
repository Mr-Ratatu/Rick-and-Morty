package com.rick.and.morti.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rick.and.morti.data.model.CharacterResult
import com.rick.and.morti.databinding.ItemCharacterBinding

class CharacterListAdapter(private val listener: CharacterListener) :
    RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    interface CharacterListener {
        fun onClickCharacter(result: CharacterResult)
    }

    private val item = ArrayList<CharacterResult>()

    fun setData(item: List<CharacterResult>) {
        this.item.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), listener
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int = item.size

    class ViewHolder(val binding: ItemCharacterBinding, private val listener: CharacterListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: CharacterResult) {
            binding.apply {
                root.setOnClickListener { listener.onClickCharacter(result) }
                items = result
                executePendingBindings()
            }
        }

    }

}