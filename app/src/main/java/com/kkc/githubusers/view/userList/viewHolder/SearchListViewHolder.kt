package com.kkc.githubusers.view.userList.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kkc.usecase.entity.SearchListItem

abstract class SearchListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(searchListItem: com.kkc.usecase.entity.SearchListItem)
}