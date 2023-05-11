package com.kkc.localdata.repository

import com.kkc.localdata.roomData.BookMark
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface BookMarkRepository {
    fun getAllBookMarks(): Observable<List<BookMark>>
    fun getCategoryBookMarks(category: String): Single<List<BookMark>>
    fun addBookMark(userId: Long, category: String): Single<Int>
    fun deleteBookMark(bookmarkIdx: Int): Completable
}
