package com.kkc.usecase.userList

import com.kkc.usecase.entity.SearchListItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface UserListUseCase {
    fun searchUserList(keyword: String): Single<List<SearchListItem>>
    fun getChangedBookMarkSection(): Observable<List<SearchListItem>>
    fun addBookMark(userIdx: Long, category: String): Single<Int>
    fun removeBookMark(bookMarkIdx: Int): Completable
}
