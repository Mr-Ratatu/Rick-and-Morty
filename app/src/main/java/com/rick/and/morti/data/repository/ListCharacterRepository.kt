package com.rick.and.morti.data.repository

import com.rick.and.morti.data.remote.api.ApiService
import com.rick.and.morti.data.remote.response.DataResponse
import io.reactivex.rxjava3.core.Single

class ListCharacterRepository(private val api: ApiService) {

    fun getListCharacter(): Single<DataResponse> = api.getListCharacter()

}