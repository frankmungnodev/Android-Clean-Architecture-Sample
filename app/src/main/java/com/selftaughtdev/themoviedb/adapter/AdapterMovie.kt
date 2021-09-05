package com.selftaughtdev.themoviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.selftaughtdev.domain.movie.model.Movie
import com.selftaughtdev.themoviedb.databinding.ItemMoviesBinding

class AdapterMovie constructor(
    private val click: (Movie) -> Unit
) : PagingDataAdapter<Movie, AdapterMovie.MovieViewHolder>(movieDiff) {
    
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
            holder.itemView.setOnClickListener { click(movie) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviesBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding) { getItem(it)?.let { movie -> click(movie) } }
    }

    class MovieViewHolder(
        private val binding: ItemMoviesBinding,
        private val itemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cardView.setOnClickListener { itemClicked(bindingAdapterPosition) }
        }

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    companion object {
        val movieDiff = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}