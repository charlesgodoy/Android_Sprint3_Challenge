package com.example.caz.pokemon_api;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    List<String> pokemonList;
    public Context context;

    public PokemonAdapter(List<String> pokemons, Context context) {
        this. pokemonList = pokemons;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameView;
        ImageView spriteView;
        TextView idView;
        TextView abilitiesView;
        TextView typesView;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            nameView = itemView.findViewById(R.id.name_view);

        }

    }


    @NonNull
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemon_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.ViewHolder viewHolder, final int position) {

        String name = pokemonList.get(position);

        viewHolder.nameView.setText(name);

        viewHolder.nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), pokemonList.get(position).toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, PokemonDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("selectedPokemon", pokemonList.get(position));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
}
