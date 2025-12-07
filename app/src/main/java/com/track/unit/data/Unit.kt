package com.track.unit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "units")
data class Units(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "recorded date")
    val today: Date = Date(),

    @ColumnInfo(name = "last date")
    val lastDate: Date,

    @ColumnInfo(name = "consumed days")
    val consumedDays: Int = today.compareTo(lastDate),

    @ColumnInfo(name = "unit")
    val unit: Int,

    @ColumnInfo(name = "price")
    val price: Double = 5.0,

    @ColumnInfo(name = "amount")
    val amount: Double = unit * price,


)

//1764121231964