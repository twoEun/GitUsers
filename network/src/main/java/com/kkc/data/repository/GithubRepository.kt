package com.kkc.data.repository

import com.kkc.data.entity.UserData
import io.reactivex.rxjava3.core.Single

interface GithubRepository {
    fun searchGithubUsers(keyword: String): Single<List<UserData>>
}
