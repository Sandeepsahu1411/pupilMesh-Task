package com.example.pupilmeshtask.data_layer.room_db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pupilmeshtask.data_layer.room_db.entity.MangaEntity

@Dao
interface MangaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mangaList: List<MangaEntity>)

    @Query("SELECT * FROM manga_table")
    fun getAllManga(): PagingSource<Int, MangaEntity>

    @Query("DELETE FROM manga_table")
    suspend fun clearAll()
}
