package com.track.unit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "units")
data class Units(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "today")
    val today: Date = Date(),

    @ColumnInfo(name = "unit")
    val unit : Int,

    @ColumnInfo(name = "price")
    val price: Double = 5.0,

    @ColumnInfo(name = "amount")
    val amount: Double = unit*price
)
