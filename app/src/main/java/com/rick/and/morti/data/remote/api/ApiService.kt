package com.rick.and.morti.data.remote.api

import com.rick.and.morti.data.remote.response.DataResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    fun getListCharacter(): Single<DataResponse>

}