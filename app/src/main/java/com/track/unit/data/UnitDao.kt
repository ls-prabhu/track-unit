package com.track.unit.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface UnitDao {
    @Insert
    suspend fun insert(unit: Units)

    @Update
    suspend fun update(unit: Units)

    @Delete
    suspend fun delete(unit: Units)

    @Query("SELECT * FROM units ORDER BY id ASC")
    fun getAllUnits(): Flow<List<Units>>

    @Query("SELECT * FROM units WHERE id = :id")
    fun getUnit(id: Int): Flow<Units?>

    @Query(value = "SELECT `last date` FROM units ORDER BY id DESC LIMIT 1")
    suspend fun getLastDate(): Date?
}

