package com.kkc.localdata.repository

import com.kkc.localdata.dataSource.BookMarkDataSource
import com.kkc.localdata.entity.Constants
import com.kkc.localdata.roomData.BookMark
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BookMarkRepositoryImpl @Inject constructor(
    private val bookmarkSource: BookMarkDataSource
) : BookMarkRepository {
    override fun getAllBookMarks(): Observable<List<BookMark>> {
        return bookmarkSource.getAllBookMarks()
    }

    override fun getCategoryBookMarks(category: String): Single<List<BookMark>> {
        return bookmarkSource.getCategoryBookMarks(category)
    }

    override fun addBookMark(userId: Long, category: String): Single<Int> {
        val bookMark = BookMark(0, userId, category)
        val addBookMark = bookmarkSource.addBookMark(bookMark)
        val savedIndex = bookmarkSource.getLastIndex(category)
        return addBookMark.concatMap {
            savedIndex
        }.doOnError {
            Single.just(Constants.ADD_BOOK_MARK_FAIL)
        }
    }

    override fun deleteBookMark(bookmarkIdx: Int): Completable {
        return bookmarkSource.deleteBookMark(bookmarkIdx)
    }
}
