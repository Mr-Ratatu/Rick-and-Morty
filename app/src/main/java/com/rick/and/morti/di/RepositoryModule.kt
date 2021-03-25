package com.rick.and.morti.di

import com.rick.and.morti.data.repository.ListCharacterRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { ListCharacterRepository(get(), get()) }

}