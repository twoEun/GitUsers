package com.kkc.githubusers.view.userDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.kkc.githubusers.R
import com.kkc.githubusers.base.BaseViewModel
import com.kkc.usecase.entity.SearchItem
import com.kkc.githubusers.util.SingleEvent
import com.kkc.localdata.entity.Constants.ADD_BOOK_MARK_FAIL
import com.kkc.localdata.repository.BookMarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val bookMarkRepository: BookMarkRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val argumentSearchItem = "search_item"
    private lateinit var userItemOrigin: SearchItem

    private val _noItem = MutableLiveData<SingleEvent<Unit>>()
    val noItem: LiveData<SingleEvent<Unit>>
        get() = _noItem

    private val _photo = MutableLiveData<String?>()
    val photo: LiveData<String?>
        get() = _photo

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    private val _description = MutableLiveData<String?>()
    val description: LiveData<String?>
        get() = _description

    private val _isBookMarked = MutableLiveData<Boolean>()
    val isBookMarked: LiveData<Boolean>
        get() = _isBookMarked

    init {
        savedStateHandle.get<com.kkc.usecase.entity.SearchItem>(argumentSearchItem)?.let {
            userItemOrigin = it
            _photo.postValue(it.photo)
            _userName.postValue(it.userName)
            _description.postValue(it.description)
            _isBookMarked.postValue(it.isBookMarked)
        } ?: kotlin.run {
            _noItem.postValue(SingleEvent(Unit))
        }
    }

    fun onClickBookMarkIcon() {
        if (userItemOrigin.isBookMarked) {
            removeBookMark(userItemOrigin.bookMarkIdx)
        } else {
            addBookMark(userItemOrigin.userIdx, userItemOrigin.searchCategory)
        }
    }

    private fun addBookMark(userId: Long, category: String) {
        bookMarkRepository.addBookMark(userId, category)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ savedIndex ->
                if (savedIndex == ADD_BOOK_MARK_FAIL) {
                    _toastRes.postValue(SingleEvent(R.string.add_book_mark_fail))
                } else {
                    userItemOrigin.isBookMarked = true
                    userItemOrigin.bookMarkIdx = savedIndex
                    _isBookMarked.postValue(true)
                }
            }, { err ->
                err.printStackTrace()
            })
    }

    private fun removeBookMark(bookmarkIdx: Int) {
        bookMarkRepository.deleteBookMark(bookmarkIdx)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                userItemOrigin.isBookMarked = false
                userItemOrigin.bookMarkIdx = ADD_BOOK_MARK_FAIL
                _isBookMarked.postValue(false)
            }
    }
}
