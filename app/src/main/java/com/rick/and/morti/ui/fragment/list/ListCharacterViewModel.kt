package com.rick.and.morti.ui.fragment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rick.and.morti.common.base.BaseViewModel
import com.rick.and.morti.common.utils.Resource
import com.rick.and.morti.data.remote.response.DataResponse
import com.rick.and.morti.data.repository.ListCharacterRepository
import com.rick.and.morti.extension.applySchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

class ListCharacterViewModel(private val repository: ListCharacterRepository) : BaseViewModel() {

    private val _listCharacter = MutableLiveData<Resource<DataResponse>>()
    val listCharacter: LiveData<Resource<DataResponse>> get() = _listCharacter

    init {
        getListCharacter()
    }

    private fun getListCharacter() {
        repository.getListCharacter()
            .applySchedulers()
            .doOnSubscribe { _listCharacter.postValue(Resource.isLoading()) }
            .subscribeBy(
                onSuccess = {
                    _listCharacter.postValue(Resource.isSuccess(it))
                },
                onError = {
                    it.printStackTrace()
                    _listCharacter.postValue(Resource.isError(it))
                }
            )
            .disposeOnCleared()
    }

}