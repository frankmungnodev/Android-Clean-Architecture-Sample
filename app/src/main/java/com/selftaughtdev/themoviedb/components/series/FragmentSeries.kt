package com.selftaughtdev.themoviedb.components.series

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.selftaughtdev.themoviedb.BR
import com.selftaughtdev.themoviedb.R
import com.selftaughtdev.themoviedb.base.BaseFragment
import com.selftaughtdev.themoviedb.components.home.HomeViewModel
import com.selftaughtdev.themoviedb.components.movies.MoviesViewModel
import com.selftaughtdev.themoviedb.databinding.FragmentHomeBinding
import com.selftaughtdev.themoviedb.databinding.FragmentMoviesBinding
import com.selftaughtdev.themoviedb.databinding.FragmentSeriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSeries : BaseFragment<FragmentSeriesBinding, SeriesViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_series

    override val title: String
        get() = getString(R.string.series)

    override val viewModel: SeriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun observeViewModel() {

    }
}