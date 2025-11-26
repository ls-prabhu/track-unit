package com.track.unit.data

import kotlinx.coroutines.flow.Flow

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
}