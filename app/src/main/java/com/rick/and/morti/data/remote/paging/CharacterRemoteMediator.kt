package com.rick.and.morti.data.remote.paging

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rick.and.morti.data.local.db.DatabaseManager
import com.rick.and.morti.data.model.CharacterResult
import com.rick.and.morti.data.model.PageKey
import com.rick.and.morti.data.remote.api.ApiService
import retrofit2.HttpException
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val api: ApiService,
    private val db: DatabaseManager
) : RemoteMediator<Int, CharacterResult>() {

    private val characterDao = db.getCharacterDao()
    private val keyDao = db.getPageKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterResult>
    ): MediatorResult {

        val loadKey = when (loadType) {
            LoadType.REFRESH -> 1

            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()

                val remoteKey: PageKey? = db.withTransaction {
                    if (lastItem?.id != null) {
                        keyDao.getNextPageKey(lastItem.id)
                    } else null
                }

                if (remoteKey?.nextPage == null) {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }

                val uri = Uri.parse(remoteKey.nextPage)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageQuery?.toInt()
            }
        }

        try {
            val response = api.getListCharacter(loadKey ?: 1)
            val resBody = response.body()
            val pageInfo = resBody?.info
            val character = resBody?.characterResult

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.clearAll()
                    keyDao.clearAll()
                }
                character?.forEach {
                    it.page = loadKey
                    keyDao.insertOrReplace(PageKey(it.id, pageInfo?.next))
                }
                character?.let { characterDao.insert(it) }

            }

            return MediatorResult.Success(endOfPaginationReached = resBody?.info?.next == null)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

}