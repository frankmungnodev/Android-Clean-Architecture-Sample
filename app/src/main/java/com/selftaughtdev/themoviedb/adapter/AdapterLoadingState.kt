package com.selftaughtdev.themoviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selftaughtdev.themoviedb.databinding.ItemNetworkStateBinding
import com.selftaughtdev.themoviedb.exception.GlobalErrorHandler
import com.selftaughtdev.themoviedb.util.hide
import com.selftaughtdev.themoviedb.util.show

class AdapterLoadingState(
    private val globalErrorHandler: GlobalErrorHandler,
    private val retry: () -> Unit
) : LoadStateAdapter<AdapterLoadingState.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNetworkStateBinding.inflate(inflater, parent, false)
        return LoadStateViewHolder(binding, globalErrorHandler) { retry() }
    }

    class LoadStateViewHolder(
        private val binding: ItemNetworkStateBinding,
        private val globalErrorHandler: GlobalErrorHandler,
        private val retry: () -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val progress = binding.progressBarItem
        private val retryBtn = binding.retyBtn
        private val textErrorMessage = binding.errorMsgItem

        init {
            retryBtn.setOnClickListener { retry() }
        }

        fun bind(loadState: LoadState) {
            binding.executePendingBindings()

            if (loadState is LoadState.Loading) progress.show()
            else progress.hide()

            textErrorMessage.isVisible = loadState is LoadState.Error
            retryBtn.isVisible = loadState is LoadState.Error

            if (loadState is LoadState.Error) {
                textErrorMessage.text = globalErrorHandler.getMessageForUser(loadState.error)
            }
        }
    }
}