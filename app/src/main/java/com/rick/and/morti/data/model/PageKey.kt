package com.rick.and.morti.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page")
data class PageKey(
    @PrimaryKey
    val id: Int,

    val nextPage: String?
)