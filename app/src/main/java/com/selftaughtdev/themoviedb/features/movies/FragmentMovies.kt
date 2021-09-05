package com.selftaughtdev.themoviedb.features.movies

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.selftaughtdev.domain.movie.model.Movie
import com.selftaughtdev.themoviedb.BR
import com.selftaughtdev.themoviedb.R
import com.selftaughtdev.themoviedb.adapter.AdapterLoadingState
import com.selftaughtdev.themoviedb.adapter.AdapterMovie
import com.selftaughtdev.themoviedb.base.BaseFragment
import com.selftaughtdev.themoviedb.databinding.FragmentMoviesBinding
import com.selftaughtdev.themoviedb.exception.GlobalErrorHandler
import com.selftaughtdev.themoviedb.util.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FragmentMovies : BaseFragment<FragmentMoviesBinding, MoviesViewModel>() {

    @Inject
    lateinit var globalErrorHandler: GlobalErrorHandler

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_movies

    override val title: String
        get() = getString(R.string.movie)

    override val viewModel: MoviesViewModel by viewModels()

    private lateinit var gridLayoutManager: GridLayoutManager

    private var adapterMovie = AdapterMovie { }
    private lateinit var footerAdapter: AdapterLoadingState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        binding.swipeRefreshMovies.setOnRefreshListener { adapterMovie.refresh() }
    }

    private fun setUpAdapter() {
        gridLayoutManager = GridLayoutManager(context, 3)

        footerAdapter = AdapterLoadingState(globalErrorHandler) { adapterMovie.retry() }

        lifecycleScope.launch {
            binding.recyclerMovies.apply {
                layoutManager = gridLayoutManager
                setHasFixedSize(true)
                adapter = adapterMovie.withLoadStateFooter(footerAdapter)
            }
            adapterMovie.addLoadStateListener { loadStates ->
                val refreshLoadState = loadStates.refresh
                binding.recyclerMovies.isVisible = refreshLoadState !is LoadState.Error
                binding.swipeRefreshMovies.isRefreshing = refreshLoadState is LoadState.Loading
                binding.layoutErrorMessage.isVisible = refreshLoadState is LoadState.Error

                if (refreshLoadState is LoadState.Error) {
                    binding.txtError.text =
                        globalErrorHandler.getMessageForUser(refreshLoadState.error)
                    binding.btnRetry.setOnClickListener { adapterMovie.retry() }
                }
            }
        }
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == adapterMovie.itemCount && footerAdapter.itemCount > 0) 3 else 1
            }
        }
    }

    override fun observeViewModel() {
        observe(viewModel.pagingData, ::submitList)
    }

    private fun submitList(pagingData: PagingData<Movie>) {
        lifecycleScope.launch { adapterMovie.submitData(pagingData) }
    }
}