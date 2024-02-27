package com.ruben.repaso2ev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.room.Room
import com.ruben.repaso2ev.database.MoviesDatabase
import com.ruben.repaso2ev.database.entities.MovieEntity
import com.ruben.repaso2ev.database.entities.toDatabase
import com.ruben.repaso2ev.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MoviesAdapter
    private lateinit var room : MoviesDatabase//Instancia objeto Room

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Construyo le objeto room
        room = Room.databaseBuilder(this, MoviesDatabase::class.java, "movies").build()
        //llenarBBDD()
        fillDatabase()
        initUI()
    }

     private fun fillDatabase(){
         CoroutineScope(Dispatchers.IO).launch {

             val movieList = MoviesProvider.getMovies().map { it.toDatabase() }
             room.getMovieDao().deleteAllMovieList()
             room.getMovieDao().insertAll(movieList)
         }

     }

    /** Función para llenar la base de datos, tiene que se la primera que se ejecute*/
    /**
    private fun llenarBBDD() {//query: String

        //binding.progressBar.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {

            //Borro lo que haya guardado
            room.getMovieDao().deleteAllMovieList()

            val myResponse: Response<MoviesDataResponse> =
                retrofit.create(MoviesProvider::class.java).getMovies() //DUDA

            val respuestaDelProvider: MoviesDataResponse? = myResponse.body()
            if (respuestaDelProvider != null) {
                Log.i("Cuerpo de la consulta", respuestaDelProvider.toString())

                runOnUiThread {//Abrimos hilo principal
                    //adapter.updateList(respuestaDeLaAPI.superheroes)//superheros es su listado

                    binding.progressBar.isVisible = false
                }

                val lista = respuestaDelProvider.movies.map {//mapeo
                    //map es el bucle que recorre la lista
                    it.toDatabase()
                }//ahora tenemos la lista de Entities

                //Inserto
                room.getMovieDao().insertAll(lista)
            }

            /** Una vez la base de datos llena lo que hago es una consultaa la BBDD
             * para que me muestre todos los superherores que hay
             * que serán los de la búsqueda sp que se ha determinado en la API*/

            val resultado: List<MovieEntity> = room.getMovieDao().getAllMovies()
            runOnUiThread {
                adapter.updateList(resultado)
                binding.progressBar.isVisible = false
            }
        }
    }
*/
    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })
        adapter = MoviesAdapter()
        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = adapter
    }

    private fun searchByName(query: String) {//pasarle la id
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            //Ahora pido con el query los superherores que llevan ese nombre y la respuesta es una lista de ListEntity
            val myResponse: List<MovieEntity> = room.getMovieDao().getMovies("%$query%")

            if (myResponse != null) {

                runOnUiThread {

                    adapter.updateList(myResponse)
                    binding.progressBar.isVisible = false
                }
            }
        }
    }


}