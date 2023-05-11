package com.kkc.usecase.entity

import com.kkc.usecase.enum.SearchListItemType

data class SearchHeader(
    val headerTitle: String
) : SearchListItem(SearchListItemType.HEADER)
