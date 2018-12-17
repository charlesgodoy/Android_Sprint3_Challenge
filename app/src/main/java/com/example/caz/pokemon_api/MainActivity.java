package com.example.caz.pokemon_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

// Charles Godoy
// AND1 - Sprint 3

public class MainActivity extends AppCompatActivity implements PokemonListResponse{

    private static final String TAG = MainActivity.class.getSimpleName();

    EditText editText;
    Button searchButton;
    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;

    LinearLayoutManager layoutManager;

    List<String> pokemonNames;
    List<String> filteredPokemonNames;          // stores filtered pokemon names here


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        searchButton = findViewById(R.id.search_button);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        loadPokemons();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String querySearch = editText.getText().toString();
                if(querySearch == null || querySearch.equals("")){

                    filteredPokemonNames = new ArrayList<>(pokemonNames);
                    return;

                }

                filteredPokemonNames.clear();       // clears previous searches

                for(int i = 0; i < pokemonNames.size(); i++) {

                    if(pokemonNames.get(i).contains(querySearch) || pokemonNames.get(i).startsWith(querySearch)){

                        filteredPokemonNames.add(pokemonNames.get(i));

                    }
                }

                Log.d("updated", filteredPokemonNames.toString());

                // tells recyclerview that the data has been changed
                recyclerView.getAdapter().notifyDataSetChanged();

            }
        });
    }



    private void loadPokemons() {

        FetchPokemonList task = new FetchPokemonList();

        task.pokeListener = this;       // set pokemon listener to current activity

        task.execute(PokemonAPI.getPokemons());         // asynctask part

    }

/////////////////////////////////////////////////
//    private List<Pokemon> loadPokemonList(){
//
//        List<Pokemon> pokemons = new ArrayList<>();
//
//        return pokemons;
//    }
////////////////////////////////////////////////

    @Override
    public void onPokemonsLoaded(List<Pokemon> pokemonList) {

    }

    @Override
    public void onPokemonNamesLoaded(List<String> pokemonNames) {

        if(pokemonNames != null) {

            Log.d("Pokemon: ", pokemonNames.toString());

            this.filteredPokemonNames = new ArrayList<>(pokemonNames);
            this.pokemonNames = new ArrayList<>(pokemonNames);

            recyclerView.setAdapter(new PokemonAdapter(filteredPokemonNames, getApplicationContext()));

        }

    }
}
