package com.example.sistemascasa.tigie.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreference {

    public static final String PREFS_NAME = "APP_FAVS";
    public static final String FAVORITES = "Favourite_Fraction";

    public SharedPreference() {
        super();
    }

    public void saveFavorites(Context context, List<ActivityFavFraction> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, ActivityFavFraction favs) {
        List<ActivityFavFraction> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<ActivityFavFraction>();
        favorites.add(favs);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, ActivityFavFraction favs) {
        ArrayList<ActivityFavFraction> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(favs);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<ActivityFavFraction> getFavorites(Context context) {
        SharedPreferences settings;
        List<ActivityFavFraction> favorites;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            ActivityFavFraction[] favoriteItems = gson.fromJson(jsonFavorites, ActivityFavFraction[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<ActivityFavFraction>(favorites);
        } else
            return null;

        return (ArrayList<ActivityFavFraction>) favorites;
    }
}