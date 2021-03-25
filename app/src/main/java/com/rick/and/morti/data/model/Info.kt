package com.rick.and.morti.data.model

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("count")
    val count: Int,

    val pages: Int,

    val next: String?,

    val prev: String?
)
