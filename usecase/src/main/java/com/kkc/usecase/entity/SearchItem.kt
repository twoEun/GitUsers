package com.kkc.usecase.entity

import com.kkc.localdata.entity.Constants.ADD_BOOK_MARK_FAIL
import com.kkc.usecase.enum.SearchListItemType

data class SearchItem(
    var searchCategory: String,
    val userIdx: Long,
    var isBookMarked: Boolean = false,
    var bookMarkIdx: Int = ADD_BOOK_MARK_FAIL,
    val userName: String,
    val photo: String?,
    val description: String?
) : SearchListItem(SearchListItemType.ITEM), java.io.Serializable
