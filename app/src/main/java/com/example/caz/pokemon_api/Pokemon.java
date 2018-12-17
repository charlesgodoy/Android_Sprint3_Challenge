package com.example.caz.pokemon_api;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {

    private String name;
    private String spriteUrl;
    private String id;
    private List<String> abilities;
    private List<String> types;

    public Pokemon(String name, String spriteUrl, String id, List<String> abilities, List<String> types) {
        this.name = name;
        this.spriteUrl = spriteUrl;
        this.id = id;
        this.abilities = abilities;
        this.types = types;
    }

    public Pokemon() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }


    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", spriteUrl='" + spriteUrl + '\'' +
                ", id='" + id + '\'' +
                ", abilities=" + abilities +
                ", types=" + types +
                '}';
    }
}
