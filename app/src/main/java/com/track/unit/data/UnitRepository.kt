package com.track.unit.data

import kotlinx.coroutines.flow.Flow
import java.util.Date

class UnitRepository(private val unitDao: UnitDao){
    val readAllData: Flow<List<Units>> = unitDao.getAllUnits()

    suspend fun addUnit(unit: Units){
        unitDao.insert(unit)
    }

    suspend fun updateUnit(unit: Units){
        unitDao.update(unit)
    }

    suspend fun deleteUnit(unit: Units){
        unitDao.delete(unit)
    }

    fun getUnitById(id: Int): Flow<Units?> {
        return unitDao.getUnit(id)
    }

    suspend fun insertFullData(Unit: Int, SelectedDate: Long){
        val lastDate = unitDao.getLastDate()?: Date()
        val entryDate = Date(SelectedDate)

        val newUnit = Units(
            today = entryDate,
            lastDate = lastDate,
            consumedDays = ((entryDate.time - lastDate.time) / (1000 * 60 * 60 * 24)).toInt(),
            unit = Unit
        )

        unitDao.insert(newUnit)

    }
}