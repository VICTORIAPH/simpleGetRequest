package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pokemon.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var queue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        queue = Volley.newRequestQueue(this)
        setContentView(binding.root)
        // actualizar.setOnClickListener {
        //   mostrar.setText(pokemon.text.toString())
        //}

        binding.btnUpdatePokemon.setOnClickListener {
            val pokemones = getPokemonList(binding.etPokemonAmount.text.toString().toInt())

        }

    }

    //dependiendo de lo que ingresamos en nuestra lista sera
    //los pokemones que nos de en "ListAmount"
    fun getPokemonList(listAmount: Int) {
        val url = "https://pokeapi.co/api/v2/pokemon/?limit=${listAmount}"

        val jsonRequest = JsonObjectRequest(url, Response.Listener<JSONObject> { response ->
            Log.i("JSONRESPONSE", response.toString())

        },
            Response.ErrorListener { error ->
                Log.w("JSONRESPONSE", error.message as String)
            })

        queue.add(jsonRequest)
    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll("Stopped")
    }
}