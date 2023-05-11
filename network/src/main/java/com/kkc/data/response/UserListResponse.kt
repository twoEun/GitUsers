package com.kkc.data.response

import com.google.gson.annotations.SerializedName
import com.kkc.data.entity.UserData

data class UserListResponse(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_results")
    val containIncompleteResult: Boolean,

    @SerializedName("items")
    val userList: List<UserData>
)
