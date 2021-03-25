package com.rick.and.morti.data.remote.response


import com.google.gson.annotations.SerializedName
import com.rick.and.morti.data.model.CharacterResult
import com.rick.and.morti.data.model.Info

data class DataResponse(
    @SerializedName("results")
    val characterResult: List<CharacterResult>,

    val info: Info
)