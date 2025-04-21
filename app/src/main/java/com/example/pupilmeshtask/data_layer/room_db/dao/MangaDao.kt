package com.example.pupilmeshtask.data_layer.room_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.pupilmeshtask.data_layer.room_db.entity.MangaEntity

@Dao
interface MangaDao {
    @Query("SELECT * FROM manga_table")
    fun getAllManga(): PagingSource<Int, MangaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(manga: List<MangaEntity>)

    @Query("DELETE FROM manga_table")
    suspend fun clearAll()
}