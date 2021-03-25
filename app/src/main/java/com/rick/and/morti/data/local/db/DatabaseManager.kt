package com.rick.and.morti.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rick.and.morti.data.local.converter.ListConverter
import com.rick.and.morti.data.local.dao.CharacterDao
import com.rick.and.morti.data.local.dao.PageKeyDao
import com.rick.and.morti.data.model.CharacterResult
import com.rick.and.morti.data.model.PageKey

@Database(entities = [CharacterResult::class, PageKey::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao

    abstract fun getPageKeyDao(): PageKeyDao

}