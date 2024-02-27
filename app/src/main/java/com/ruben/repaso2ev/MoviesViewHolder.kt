package com.ruben.repaso2ev

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ruben.repaso2ev.database.entities.MovieEntity
import com.ruben.repaso2ev.databinding.ItemLayoutBinding
import com.squareup.picasso.Picasso

class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemLayoutBinding.bind(view)
    fun bind(movieItemResponse: MovieEntity) {

        binding.tvTitle.text= movieItemResponse.title
        binding.tvReleaseDate.text = movieItemResponse.releaseDate
        binding.tvDuration.text = movieItemResponse.duration

        Picasso.get().load(movieItemResponse.image).into(binding.ivMovie)

        binding.tvSynopsis.text = movieItemResponse.synopsis
        binding.tvGenre.text = movieItemResponse.genre
        binding.tvDirector.text = movieItemResponse.director
        binding.tvLeadActor.text = movieItemResponse.leadActor
        binding.tvWriters.text = movieItemResponse.writer1 +"\n"+ movieItemResponse.writer2 +"" +
                "\n"+ movieItemResponse.writer3+"\n"+ movieItemResponse.writer4

    }
}

