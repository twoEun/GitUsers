package com.kkc.data

import com.kkc.data.dataSource.GithubDataSource
import com.kkc.data.repository.GithubRepository
import com.kkc.data.repository.GithubRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    internal fun provideGithubRepository(githubDataSource: GithubDataSource): GithubRepository {
        return GithubRepositoryImpl(githubDataSource)
    }
}
