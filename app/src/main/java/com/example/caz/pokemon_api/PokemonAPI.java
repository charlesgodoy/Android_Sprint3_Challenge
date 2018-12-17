package com.example.caz.pokemon_api;

public class PokemonAPI {

    public static final String POKEMON_BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static String getPokemons() {
        return POKEMON_BASE_URL;
    }

    public static String getPokemonsBySearchKey (String searchKey) {

        return POKEMON_BASE_URL + searchKey + "/";
    }

}
