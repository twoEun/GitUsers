package com.kkc.localdata

import com.kkc.localdata.repository.BookMarkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataRepositoryModule {
    @Provides
    @Singleton
    fun provideBookMarkRepository(bookMarkDataBase: BookMarkDatabase): com.kkc.localdata.repository.BookMarkRepository {
        return BookMarkRepositoryImpl(bookMarkDataBase.bookmarkDataSource())
    }
}
