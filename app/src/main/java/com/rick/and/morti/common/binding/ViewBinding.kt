package com.rick.and.morti.common.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.rick.and.morti.extension.setEpisode
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("episodes")
fun ChipGroup.setEpisode(episode: List<String>) {
    for (ep in episode) {
        val chip = Chip(context)
            .apply { setEpisode(ep) }
        this.addView(chip)
    }
}

@BindingAdapter("type")
fun TextView.setType(type: String) {
    if (type.isEmpty())
        this.text = "Отсутствует"
    else
        this.text = type
}

@BindingAdapter("dateFormat")
fun TextView.setDateFormat(date: String?) {
    val parseDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val formatter = SimpleDateFormat("d MMM yyyy, HH:mm", Locale.getDefault())

    try {
        this.text = formatter.format(parseDate.parse(date))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}