package com.kkc.localdata.roomData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookMark(
    @PrimaryKey(autoGenerate = true)
    val index: Int,

    @ColumnInfo(name = "userIndex")
    val userIdx: Long,

    @ColumnInfo(name = "category")
    val category: String
)
