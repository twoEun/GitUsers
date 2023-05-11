package com.kkc.data.dataSource

import com.kkc.data.response.UserListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubDataSource {
    @GET("/search/users")
    fun getUserList(@Query("q") keyword: String): Single<UserListResponse>
}
