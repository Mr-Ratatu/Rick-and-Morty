package com.rick.and.morti.data.remote.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rick.and.morti.common.utils.Constants.Companion.START_INDEX
import com.rick.and.morti.data.model.CharacterResult
import com.rick.and.morti.data.remote.api.ApiService
import com.rick.and.morti.data.remote.response.DataResponse
import java.io.IOException

class CharacterPagingSource(
    private val api: ApiService
) : PagingSource<Int, CharacterResult>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterResult>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResult> {
        val position = params.key ?: START_INDEX

        return try {
            var nextPageNumber: Int? = null
            val response = api.getListCharacter(position)
            val pagedResponse = response.body()
            val data = pagedResponse?.characterResult

            if (pagedResponse?.info?.next != null) {
                val uri = Uri.parse(pagedResponse.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            toLoadResult(data, nextPageNumber)
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

    private fun toLoadResult(
        response: List<CharacterResult>?,
        nextPage: Int?
    ): LoadResult<Int, CharacterResult> {
        return LoadResult.Page(
            data = response.orEmpty(),
            prevKey = null,
            nextKey = nextPage
        )
    }

}