package com.kkc.githubusers.view.userList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kkc.githubusers.R
import com.kkc.githubusers.base.BaseFragment
import com.kkc.usecase.entity.SearchListItem
import com.kkc.githubusers.databinding.FragmentUserListBinding
import com.kkc.usecase.entity.SearchItem
import com.kkc.githubusers.view.userList.adapter.SearchItemAdapter
import com.kkc.githubusers.view.userList.interfaces.UserItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment :
    BaseFragment<FragmentUserListBinding>(R.layout.fragment_user_list),
    UserItemClickListener {
    private val userListViewModel by viewModels<UserListViewModel>()
    private val searchListAdapter = SearchItemAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.userList = userListViewModel

        userSectionsObserve()
    }

    override fun uiInit() {
        binding.searchResult.adapter = searchListAdapter
        binding.searchResult.itemAnimator?.changeDuration = 0L
    }

    private fun userSectionsObserve() {
        val userSectionsObserver = Observer<List<com.kkc.usecase.entity.SearchListItem>> { sections ->
            searchListAdapter.submitList(sections)
        }

        userListViewModel.searchList.observe(viewLifecycleOwner, userSectionsObserver)
    }

    override fun onUserItemClick(userItem: com.kkc.usecase.entity.SearchItem) {
        findNavController().navigate(UserListFragmentDirections.goDetail(userItem))
    }

    override fun onBookMarkClick(userItem: com.kkc.usecase.entity.SearchItem) {
        userListViewModel.requestModifyBookMark(userItem)
    }
}
