package com.kkc.githubusers.view.userList.viewHolder

import com.kkc.usecase.entity.SearchHeader
import com.kkc.usecase.entity.SearchListItem
import com.kkc.githubusers.databinding.ItemUserItemHeaderBinding

class SearchHeaderViewHolder(private val binding: ItemUserItemHeaderBinding) : SearchListViewHolder(binding.root) {
    override fun onBind(searchListItem: com.kkc.usecase.entity.SearchListItem) {
        binding.header = searchListItem as com.kkc.usecase.entity.SearchHeader
    }
}
