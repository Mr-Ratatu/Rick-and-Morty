package com.rick.and.morti.ui.fragment.list

import androidx.paging.PagingData
import com.rick.and.morti.data.model.CharacterResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

data class _FlowViewState(
    val _feed: MutableStateFlow<PagingData<CharacterResult>?> = MutableStateFlow(null)
)

data class FlowViewState(private val _viewState: _FlowViewState) {
    val feed: Flow<PagingData<CharacterResult>> = _viewState._feed.filterNotNull()
}