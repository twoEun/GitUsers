package com.kkc.githubusers.view.userList.viewHolder

import com.kkc.usecase.entity.SearchListItem
import com.kkc.githubusers.databinding.ItemUserItemBinding
import com.kkc.usecase.entity.SearchItem

class SearchItemViewHolder(private val binding: ItemUserItemBinding) : SearchListViewHolder(binding.root) {
    override fun onBind(searchListItem: com.kkc.usecase.entity.SearchListItem) {
        binding.userItem = searchListItem as com.kkc.usecase.entity.SearchItem
    }
}