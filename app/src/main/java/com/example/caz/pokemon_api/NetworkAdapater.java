package com.example.caz.pokemon_api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkAdapater {

    public static final String POKEMON_BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static String GetRespFromHttpUrl(String inputUrl) {

        URL url = null;
        try {
            url = new URL(inputUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            InputStream is = con.getInputStream();

            Scanner scan = new Scanner(is);
            scan.useDelimiter("\\A");

            boolean hasinput = scan.hasNext();
            if(hasinput){
                return scan.next();
            }else{
                return null;
            }

        }catch (FileNotFoundException fne){
            fne.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            con.disconnect();
        }
        return null;
    }

}