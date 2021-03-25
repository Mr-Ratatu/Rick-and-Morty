package com.rick.and.morti.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "result")
data class CharacterResult(
    var page: Int?,

    val created: String,

    val episode: List<String>,

    val gender: String,

    @PrimaryKey val id: Int,

    val image: String,

    val name: String,

    val species: String,

    val status: String,

    val type: String,

    val url: String
) : Parcelable