package com.example.caz.pokemon_api;

import android.os.AsyncTask;

public class FetchPokemonDetail extends AsyncTask<String, String, String>  {

    PokemonDetailResponse listener;

    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];

        String response = NetworkAdapater.GetRespFromHttpUrl(url);      // call network

        return response;
    }

    @Override
    protected void onPostExecute(String s) {

        PokemonParser parser = new PokemonParser(s);
        Pokemon pokemon = parser.getPokemon();

        listener.onPokemonDetailLoaded(pokemon);
        super.onPostExecute(s);
    }
}
