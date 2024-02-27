package com.ruben.repaso2ev.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ruben.repaso2ev.database.dao.MovieDao
import com.ruben.repaso2ev.database.entities.MovieEntity


//Es un array con todas las tablas, con tantas clases entities como tablas tenga
@Database(entities = [MovieEntity::class], version = 1) //El 1 es un control de versiones, que ahora no se va a usar
abstract class MoviesDatabase: RoomDatabase() {

    //Hay dos entities pues dos funciones, una por cada tabla
    abstract fun getMovieDao(): MovieDao

    //son getter porque es para coger información de la tabla de datos

}