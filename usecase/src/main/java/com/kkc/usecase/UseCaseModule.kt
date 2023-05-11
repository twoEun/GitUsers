package com.kkc.usecase

import com.kkc.data.repository.GithubRepository
import com.kkc.localdata.repository.BookMarkRepository
import com.kkc.usecase.userList.UserListUseCase
import com.kkc.usecase.userList.UserListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideUserListUseCase(githubRepository: GithubRepository, bookMarkRepository: BookMarkRepository): UserListUseCase {
        return UserListUseCaseImpl(githubRepository, bookMarkRepository)
    }
}
