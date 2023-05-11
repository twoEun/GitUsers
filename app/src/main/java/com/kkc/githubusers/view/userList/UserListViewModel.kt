package com.kkc.githubusers.view.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kkc.githubusers.R
import com.kkc.githubusers.base.BaseViewModel
import com.kkc.githubusers.util.SingleEvent
import com.kkc.usecase.userList.UserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userListUseCase: UserListUseCase
) : BaseViewModel() {
    val keyword = MutableLiveData<String>()

    private val _searchList = MutableLiveData<List<com.kkc.usecase.entity.SearchListItem>>()
    val searchList: LiveData<List<com.kkc.usecase.entity.SearchListItem>>
        get() = _searchList

    private var bookMarkChagneDisposable: Disposable? = null
    private val _noData = MutableLiveData<Boolean>()
    val noData: LiveData<Boolean>
        get() = _noData

    init {
        getBookMarkChangedSection()
    }

    private fun getBookMarkChangedSection() {
        bookMarkChagneDisposable = userListUseCase.getChangedBookMarkSection()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ searchList ->
                if (searchList.isNotEmpty()) {
                    _noData.postValue(false)
                    _searchList.postValue(searchList)
                }
            }, { err ->
                err.printStackTrace()
            })
    }

    private fun searchUsers(keyword: String) {
        _progressVisible.postValue(true)
        userListUseCase.searchUserList(keyword)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doFinally { _progressVisible.postValue(false) }
            .subscribe({ searchList ->
                if (searchList.isEmpty()) {
                    _noData.postValue(true)
                } else {
                    _noData.postValue(false)
                    _searchList.postValue(searchList)
                }
            }, { err ->
                err.printStackTrace()
            })
    }

    fun onSearchClick() {
        val keyword = keyword.value ?: ""

        if (keyword.isBlank()) {
            _toastRes.postValue(SingleEvent(R.string.search_hint))
        } else {
            searchUsers(keyword)
        }
    }

    fun requestModifyBookMark(userItem: com.kkc.usecase.entity.SearchItem) {
        if (userItem.isBookMarked) {
            requestRemoveBookMark(userItem.bookMarkIdx)
        } else {
            requestAddBookMark(userItem.userIdx, userItem.searchCategory)
        }
    }

    private fun requestAddBookMark(userId: Long, category: String) {
        userListUseCase.addBookMark(userId, category)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    private fun requestRemoveBookMark(bookmarkIdx: Int) {
        userListUseCase.removeBookMark(bookmarkIdx)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        if (bookMarkChagneDisposable?.isDisposed == false) {
            bookMarkChagneDisposable?.dispose()
        }
    }
}
