package com.rick.and.morti.extension

import android.view.View
import com.google.android.material.chip.Chip

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Chip.setEpisode(str: String) {
    val episode = str.substring(str.length - 2)
    when {
        episode.first() == '/' ->
            this.text = str.substring(str.length - 1)
        else ->
            this.text = str.substring(str.length - 2)

    }
}