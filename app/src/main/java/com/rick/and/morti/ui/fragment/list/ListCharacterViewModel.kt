package com.rick.and.morti.ui.fragment.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rick.and.morti.data.model.CharacterResult
import com.rick.and.morti.data.repository.ListCharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ListCharacterViewModel(private val repository: ListCharacterRepository) : ViewModel() {

    private val _viewState = _FlowViewState()
    val viewState get() = FlowViewState(_viewState)

    init {
        initFeed()
    }

    private fun initFeed() {
        repository.getListCharacter().cachedIn(viewModelScope).onEach { results ->
            _viewState._feed.value = results
        }.launchIn(viewModelScope)
    }

}