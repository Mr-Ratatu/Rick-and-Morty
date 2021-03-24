package com.rick.and.morti.di

import com.rick.and.morti.ui.fragment.list.ListCharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ListCharacterViewModel(get()) }

}