package com.selftaughtdev.themoviedb.components.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.selftaughtdev.themoviedb.BR
import com.selftaughtdev.themoviedb.R
import com.selftaughtdev.themoviedb.base.BaseFragment
import com.selftaughtdev.themoviedb.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_home

    override val title: String
        get() = getString(R.string.home)

    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun observeViewModel() {
    }
}