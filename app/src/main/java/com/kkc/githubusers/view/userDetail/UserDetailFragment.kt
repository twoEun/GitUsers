package com.kkc.githubusers.view.userDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kkc.githubusers.R
import com.kkc.githubusers.base.BaseFragment
import com.kkc.githubusers.util.SingleEventObserver
import com.kkc.githubusers.databinding.FragmentUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>(R.layout.fragment_user_detail) {
    private val userDetailViewModel by viewModels<UserDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.userDetail = userDetailViewModel

        noItemObserve()
        toastResObserve()
    }

    override fun uiInit() = Unit

    private fun noItemObserve() {
        val noItemObserver = SingleEventObserver<Unit> {
            showToast(getString(R.string.no_user_item))
        }

        userDetailViewModel.noItem.observe(viewLifecycleOwner, noItemObserver)
    }

    private fun toastResObserve() {
        val toastResObserver = SingleEventObserver<Int> { toastRes ->
            showToast(getString(toastRes))
        }

        userDetailViewModel.toastRes.observe(viewLifecycleOwner, toastResObserver)
    }
}
