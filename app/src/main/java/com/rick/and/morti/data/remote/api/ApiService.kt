package com.rick.and.morti.data.remote.api

import com.rick.and.morti.data.remote.response.DataResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getListCharacter(
        @Query("page") page: Int
    ): Response<DataResponse>

}