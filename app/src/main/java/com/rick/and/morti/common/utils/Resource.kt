package com.rick.and.morti.common.utils

enum class States { SUCCESS, LOADING, ERROR }

data class Resource<out T>(val data: T?, val state: States, val error: Throwable?) {

    companion object {
        fun <T> isSuccess(data: T?): Resource<T> = Resource(data, States.SUCCESS, null)

        fun <T> isLoading(): Resource<T> = Resource(null, States.LOADING, null)

        fun <T> isError(error: Throwable?): Resource<T> = Resource(null, States.ERROR, error)
    }

}
