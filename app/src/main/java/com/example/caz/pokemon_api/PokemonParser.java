package com.example.caz.pokemon_api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PokemonParser {

    private String data;

    public PokemonParser(String data) {             // takes string for parsing
        this.data = data;
    }

    public List<String> pokemonNames() {

        List<String> pokemonNames = null;

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);
            JSONArray itemArray = jsonObject.getJSONArray("results");

            pokemonNames = new ArrayList<>();

            for(int i = 0; i < itemArray.length(); i++) {
                JSONObject pokemonDetail = itemArray.getJSONObject(i);
                pokemonNames.add(pokemonDetail.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pokemonNames;        // returns list of names
    }

    public Pokemon getPokemon() {
        JSONObject jsonObject = null;

        Pokemon pokemon = new Pokemon();

        try {
            jsonObject = new JSONObject(data);

            pokemon.setId(jsonObject.getString("id"));
            pokemon.setName(jsonObject.getString("name"));

            List<String> abilities = new ArrayList<>();
            JSONArray abilitiesArray = jsonObject.getJSONArray("abilities");    // abilities is an array, need to loop

            for(int i = 0; i < abilitiesArray.length(); i++) {

                JSONObject ability = abilitiesArray.getJSONObject(i).getJSONObject("ability");
                abilities.add(ability.getString("name"));

            }

            pokemon.setAbilities(abilities);

            List<String> types = new ArrayList<>();
            JSONArray typesArray = jsonObject.getJSONArray("types");

            for(int i = 0; i < typesArray.length(); i++) {
                JSONObject type = typesArray.getJSONObject(i).getJSONObject("type");
                types.add(type.getString("name"));
            }

            pokemon.setSpriteUrl(jsonObject.getJSONObject("sprites").getString("front_default"));
            pokemon.setTypes(types);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pokemon;

    }

}
