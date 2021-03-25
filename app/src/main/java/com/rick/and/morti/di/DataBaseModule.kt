package com.rick.and.morti.di

import androidx.room.Room
import com.rick.and.morti.common.utils.Constants.Companion.DB_NAME
import com.rick.and.morti.data.local.db.DatabaseManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataBaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(), DatabaseManager::class.java, DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    single { get<DatabaseManager>().getCharacterDao() }

}