package com.rick.and.morti.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rick.and.morti.data.model.PageKey

@Dao
interface PageKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(pageKey: PageKey)

    @Query("SELECT * FROM page WHERE id LIKE :id")
    fun getNextPageKey(id: Int): PageKey?

    @Query("DELETE FROM page")
    suspend fun clearAll()
}