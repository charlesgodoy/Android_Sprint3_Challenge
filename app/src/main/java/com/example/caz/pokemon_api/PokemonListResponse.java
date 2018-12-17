package com.example.caz.pokemon_api;

import java.util.List;

public interface PokemonListResponse {

    void onPokemonsLoaded(List<Pokemon> pokemonList);

    void onPokemonNamesLoaded(List<String> pokemonNames);

}
