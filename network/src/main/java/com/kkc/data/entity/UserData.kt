package com.kkc.data.entity

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id")
    val userId: Long,

    @SerializedName("login")
    val userName: String,

    @SerializedName("avatar_url")
    val photo: String?,

    @SerializedName("html_url")
    val description: String?
)
