package com.rick.and.morti.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rick.and.morti.data.model.CharacterResult

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: List<CharacterResult>)

    @Query("SELECT * FROM result")
    fun getCharacterList(): PagingSource<Int, CharacterResult>

    @Query("DELETE FROM result")
    suspend fun clearAll()

}