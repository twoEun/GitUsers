package com.kkc.usecase.userList

import com.kkc.data.entity.UserData
import com.kkc.data.repository.GithubRepository
import com.kkc.localdata.entity.Constants.ADD_BOOK_MARK_FAIL
import com.kkc.localdata.repository.BookMarkRepository
import com.kkc.localdata.roomData.BookMark
import com.kkc.usecase.entity.SearchHeader
import com.kkc.usecase.entity.SearchItem
import com.kkc.usecase.entity.SearchListItem
import com.kkc.usecase.entity.UserSection
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserListUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
    private val bookMarkRepository: BookMarkRepository
) : UserListUseCase {
    private var originSearchList = listOf<UserData>()
    private var latestKeyword = ""

    override fun searchUserList(keyword: String): Single<List<SearchListItem>> {
        latestKeyword = keyword
        val userList = githubRepository.searchGithubUsers(keyword)
        val bookmarkList = bookMarkRepository.getCategoryBookMarks(keyword)

        return Single.zip(userList, bookmarkList) { users, bookmarks ->
            if (users.isEmpty()) {
                emptyList()
            } else {
                originSearchList = users
                createUserSections(keyword, users, bookmarks)
            }
        }
    }

    override fun getChangedBookMarkSection(): Observable<List<SearchListItem>> {
        return bookMarkRepository.getAllBookMarks()
            .map { result ->
                val bookMark = result.filter { bookMark -> bookMark.category == latestKeyword }
                createUserSections(latestKeyword, originSearchList, bookMark)
            }
    }

    override fun addBookMark(userIdx: Long, category: String): Single<Int> {
        return bookMarkRepository.addBookMark(userIdx, category)
    }

    override fun removeBookMark(bookMarkIdx: Int): Completable {
        return bookMarkRepository.deleteBookMark(bookMarkIdx)
    }

    private fun createUserSections(keyword: String, userData: List<UserData>, bookmark: List<BookMark>): List<SearchListItem> {
        val userItemList = userData.map { user ->
            val bookMark = bookmark.find { bookMark -> bookMark.userIdx == user.userId }
            SearchItem(
                searchCategory = keyword,
                userIdx = user.userId,
                isBookMarked = bookMark != null,
                bookMarkIdx = bookMark?.index ?: ADD_BOOK_MARK_FAIL,
                userName = user.userName,
                photo = user.photo,
                description = user.description
            )
        }

        val searchedList = mutableListOf<SearchListItem>()
        userItemList
            .groupBy { it.isBookMarked }
            .map { section ->
                val headerTitle = if (section.key) {
                    "\'${keyword}\' BookMarked"
                } else {
                    "\'${keyword}\' Users"
                }
                UserSection(headerTitle, section.value)
            }.sortedBy { it.headerTitle }.forEach { section ->
                searchedList.add(SearchHeader(section.headerTitle))
                searchedList.addAll(section.searchedList)
            }

        return searchedList
    }
}
