package com.kkc.data.repository

import com.kkc.data.dataSource.GithubDataSource
import com.kkc.data.entity.UserData
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

internal class GithubRepositoryImpl @Inject constructor(
    private val githubDataSource: GithubDataSource
) : GithubRepository {
    override fun searchGithubUsers(keyword: String): Single<List<UserData>> {
        return githubDataSource.getUserList(keyword).map { response ->
            response.userList
        }
    }
}
