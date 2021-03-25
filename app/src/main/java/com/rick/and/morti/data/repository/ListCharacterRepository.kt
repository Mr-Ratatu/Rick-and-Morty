package com.rick.and.morti.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rick.and.morti.data.local.db.DatabaseManager
import com.rick.and.morti.data.model.CharacterResult
import com.rick.and.morti.data.remote.api.ApiService
import com.rick.and.morti.data.remote.paging.CharacterRemoteMediator
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class ListCharacterRepository(private val api: ApiService, private val db: DatabaseManager) {

    fun getListCharacter(): Flow<PagingData<CharacterResult>> {
        val pagingSourceFactory = { db.getCharacterDao().getCharacterList() }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 1
            ), remoteMediator = CharacterRemoteMediator(api, db),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}