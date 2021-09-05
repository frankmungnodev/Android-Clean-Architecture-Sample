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
) :
    LoadStateAdapter<AdapterLoadingState.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        val progress = holder.binding.progressBarItem
        val retryBtn = holder.binding.retyBtn
        val textErrorMessage = holder.binding.errorMsgItem

        if (loadState is LoadState.Loading) progress.show()
        else progress.hide()

        textErrorMessage.isVisible = loadState is LoadState.Error
        retryBtn.isVisible = loadState is LoadState.Error

        if (loadState is LoadState.Error) {
            textErrorMessage.text = globalErrorHandler.getMessageForUser(loadState.error)
        }
        retryBtn.setOnClickListener { retry.invoke() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder.from(parent)
    }

    class LoadStateViewHolder(val binding: ItemNetworkStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): LoadStateViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemNetworkStateBinding.inflate(inflater, parent, false)
                return LoadStateViewHolder(binding)
            }
        }
    }
}