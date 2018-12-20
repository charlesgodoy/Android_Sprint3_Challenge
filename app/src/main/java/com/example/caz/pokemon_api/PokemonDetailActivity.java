package com.example.caz.pokemon_api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PokemonDetailActivity extends AppCompatActivity implements PokemonDetailResponse{

    String selectedPokemon;
    TextView tvPokemonName;
    TextView tvAbilities;
    TextView tvTypes;
    Pokemon loadedPokemon;
    ImageView ivPokemonImage;

    Bitmap bitmap;


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
            String imageUrl = loadedPokemon.getSpriteUrl();


            new GetImageFromUrl().execute(imageUrl);
//// ----------------------------------
//            int loader = R.drawable.loader;             // added loader image in drawable folder
//
//            String imageUrl = loadedPokemon.getSpriteUrl();
//
//            ImageLoader imgLoader = new ImageLoader(getApplicationContext());
//            Log.d("ShowUrl", imageUrl);
//            imgLoader.DisplayImage(imageUrl, loader, ivPokemonImage);
//// ----------------------------------
////            Picasso.get().load(this.loadedPokemon.getSpriteUrl()).into(ivPokemonImage);
//// ----------------------------------
            tvAbilities.setText("Abilities: " + loadedPokemon.getAbilities());
            tvTypes.setText("Types: " + loadedPokemon.getTypes());
        }
    }


    public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... strings) {

            String urlDisplay = strings[0];
            bitmap = null;
            try {
                InputStream inputStream = new URL(urlDisplay).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ivPokemonImage.setImageBitmap(bitmap);
        }
    }

}
