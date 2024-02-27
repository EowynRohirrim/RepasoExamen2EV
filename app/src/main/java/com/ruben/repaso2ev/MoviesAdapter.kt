package com.ruben.repaso2ev

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ruben.repaso2ev.database.entities.MovieEntity



    class MoviesAdapter(var moviesList: List<MovieEntity> = emptyList()) : RecyclerView.Adapter<MoviesViewHolder>() {
        //He cambiado de String a int

        fun updateList(list: List<MovieEntity>) {//Ahora es una lista de List<Entity>
            moviesList = list
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
            return MoviesViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            )
        }
        override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
            holder.bind(moviesList[position])
        }
        override fun getItemCount() = moviesList.size
    }
