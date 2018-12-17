package com.example.caz.pokemon_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PokemonDetailActivity extends AppCompatActivity implements PokemonDetailResponse{

    String selectedPokemon;
    TextView tvPokemonName;
    TextView tvAbilities;
    TextView tvTypes;
    Pokemon loadedPokemon;
    ImageView ivPokemonImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        selectedPokemon = getIntent().getExtras().getString("selectedPokemon");

        Toast.makeText(getApplicationContext(), selectedPokemon.toString(), Toast.LENGTH_SHORT).show();

        tvPokemonName = findViewById(R.id.text_view_pokemon_name);
        tvPokemonName.setText(selectedPokemon);

        ivPokemonImage = findViewById(R.id.image_view_pokemon);
        tvAbilities = findViewById(R.id.text_view_abilities);
        tvTypes = findViewById(R.id.text_view_types);

        loadPokemonDetail(selectedPokemon);
    }

    private void loadPokemonDetail(String selectedPokemon) {

        FetchPokemonDetail task = new FetchPokemonDetail();
        task.listener = this;
        task.execute(PokemonAPI.getPokemonsBySearchKey(selectedPokemon));

    }


    @Override
    public void onPokemonDetailLoaded(Pokemon pokemon) {
        // get the pokemon here
        if(pokemon!=null){
            this.loadedPokemon = pokemon;


            int loader = R.drawable.loader;             // added loader image in drawable folder

            String imageUrl = loadedPokemon.getSpriteUrl();

            ImageLoader imgLoader = new ImageLoader(getApplicationContext());
            Log.d("ShowUrl", imageUrl);
            imgLoader.DisplayImage(imageUrl, loader, ivPokemonImage);

//            Picasso.get().load(this.loadedPokemon.getSpriteUrl()).into(ivPokemonImage);

            tvAbilities.setText("Abilities: " + loadedPokemon.getAbilities());
            tvTypes.setText("Types: " + loadedPokemon.getTypes());
        }
    }
}
