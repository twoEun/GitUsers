package com.kkc.githubusers.view.userList.interfaces

import com.kkc.usecase.entity.SearchItem

interface UserItemClickListener {
    fun onUserItemClick(userItem: com.kkc.usecase.entity.SearchItem)
    fun onBookMarkClick(userItem: com.kkc.usecase.entity.SearchItem)
}
