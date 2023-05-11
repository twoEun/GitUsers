package com.kkc.githubusers.view.userList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.kkc.githubusers.R
import com.kkc.usecase.entity.SearchListItem
import com.kkc.githubusers.databinding.ItemUserItemBinding
import com.kkc.usecase.enum.SearchListItemType
import com.kkc.githubusers.util.searchItemDiff
import com.kkc.githubusers.view.userList.interfaces.UserItemClickListener
import com.kkc.githubusers.view.userList.viewHolder.SearchItemViewHolder
import com.kkc.githubusers.databinding.ItemUserItemHeaderBinding
import com.kkc.githubusers.view.userList.viewHolder.SearchListViewHolder
import com.kkc.githubusers.view.userList.viewHolder.SearchHeaderViewHolder

class SearchItemAdapter(private val itemClickListener: UserItemClickListener?) : ListAdapter<com.kkc.usecase.entity.SearchListItem, SearchListViewHolder>(
    searchItemDiff
) {
    private val header = 0
    private val item = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        if (viewType == header) {
            val binding = DataBindingUtil.inflate<ItemUserItemHeaderBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_user_item_header,
                parent,
                false
            )

            return SearchHeaderViewHolder(binding)
        } else {
            val binding = DataBindingUtil.inflate<ItemUserItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_user_item,
                parent,
                false
            )

            binding.root.setOnClickListener {
                binding.userItem?.let {
                    itemClickListener?.onUserItemClick(it)
                }
            }

            binding.bookmarkIcon.setOnClickListener {
                binding.userItem?.let {
                    itemClickListener?.onBookMarkClick(it)
                }
            }

            return SearchItemViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].itemType == com.kkc.usecase.enum.SearchListItemType.HEADER) {
            header
        } else {
            item
        }
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}
