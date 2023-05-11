package com.kkc.githubusers.util

import androidx.recyclerview.widget.DiffUtil

val searchItemDiff = object : DiffUtil.ItemCallback<com.kkc.usecase.entity.SearchListItem>() {
    override fun areItemsTheSame(oldItem: com.kkc.usecase.entity.SearchListItem, newItem: com.kkc.usecase.entity.SearchListItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: com.kkc.usecase.entity.SearchListItem, newItem: com.kkc.usecase.entity.SearchListItem): Boolean {
        return if (oldItem is com.kkc.usecase.entity.SearchItem && newItem is com.kkc.usecase.entity.SearchItem) {
            oldItem.isBookMarked == newItem.isBookMarked
        } else if (oldItem is com.kkc.usecase.entity.SearchHeader && newItem is com.kkc.usecase.entity.SearchHeader) {
            oldItem.headerTitle == newItem.headerTitle
        } else {
            true
        }
    }
}
