package com.kkc.localdata

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BookMarkDataBaseModule {
    @Provides
    @Singleton
    fun provideLocalDataBase(@ApplicationContext context: Context): BookMarkDatabase {
        return BookMarkDatabase.getDatabase(context = context)
    }
}
