package com.kkc.localdata.dataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kkc.localdata.roomData.BookMark
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface BookMarkDataSource {
    @Query("SELECT * FROM BookMark WHERE `category` = :category")
    fun getCategoryBookMarks(category: String): Single<List<BookMark>>

    @Query("SELECT * FROM BookMark")
    fun getAllBookMarks(): Observable<List<BookMark>>

    @Insert
    fun addBookMark(bookMark: BookMark): Single<Unit>

    @Query("DELETE FROM BookMark WHERE `index` = :index")
    fun deleteBookMark(index: Int): Completable

    @Query("SELECT `index` FROM BookMark WHERE `category` = :category ORDER BY `index` DESC LIMIT 1")
    fun getLastIndex(category: String): Single<Int>
}
