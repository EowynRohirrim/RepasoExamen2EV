package com.ruben.repaso2ev.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ruben.repaso2ev.database.entities.MovieEntity

@Dao
interface MovieDao {
    //Select se usa query
    //Búsqueda que devuelve la tabla entera
    @Query("SELECT * FROM movie_table")
    suspend fun getAllMovies(): List<MovieEntity>

    //Select se usa query
    //Búsqueda
    @Query("SELECT * FROM movie_table WHERE title Like  :query")
    suspend fun getMovies(query: String): List<MovieEntity>

    //Select se usa query
    //Búsqueda por id
    @Query("SELECT * FROM movie_table Where id LIKE :id")
    suspend fun getAllMoviesID(id: Int): MovieEntity


    /***Métodos para borrar e insertar se necesitan si o si*/

    //Para insertar se usa insert, insertar en la tabla
    @Insert(onConflict = OnConflictStrategy.REPLACE) //onConflict si hay error de los superheroes entonces reemplaza
    suspend fun insertAll(movies: List<MovieEntity>) //objetos de la base de datos

    //Delete se usa query, para borrar la BBDD
    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovieList()

}