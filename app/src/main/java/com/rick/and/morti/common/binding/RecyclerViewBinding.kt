package com.rick.and.morti.common.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("recyclerAdapter")
fun RecyclerView.adapter(adapters: RecyclerView.Adapter<*>) {
    this.apply {
        adapter = adapters
        hasFixedSize()
    }
}