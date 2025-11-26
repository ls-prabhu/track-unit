package com.track.unit.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context


@Database(entities = [Units::class], version = 1)
@TypeConverters(Converters::class)
abstract class UnitDatabase : RoomDatabase() {
    abstract fun unitDao(): UnitDao
    companion object{
        private var INSTANCE: UnitDatabase? = null
        fun getInstance(context: Context) : UnitDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UnitDatabase::class.java,
                        "unit_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }

    }
}