package com.kkc.data

import com.kkc.data.dataSource.GithubDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataSourceModule {
    @Provides
    @Singleton
    fun provideGithubDataSource(retrofit: Retrofit): GithubDataSource {
        return retrofit.create(GithubDataSource::class.java)
    }
}
