package com.example.caz.pokemon_api;

import android.os.AsyncTask;

import java.util.List;

public class FetchPokemonList extends AsyncTask<String, Void, String> {


    PokemonListResponse pokeListener;       // need to set in main activity

    @Override
    protected String doInBackground(String... strings) {

        String response = NetworkAdapater.GetRespFromHttpUrl(strings[0]);

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        PokemonParser parser = new PokemonParser(s);
        List<String> pokemonNames = parser.pokemonNames();

        // sends data
        pokeListener.onPokemonNamesLoaded(pokemonNames);
    }
}
