package com.example.sistemascasa.tigie.settings;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.R;

import java.util.ArrayList;
import java.util.List;


public class FavListFragment extends Fragment implements OnItemClickListener, OnItemLongClickListener {

    public static final String ARG_ITEM_ID = "favorite_list";

    Activity activity;
    ListView favListView;
    List<ActivityFavFraction> favourites;
    FavListAdapter favListAdapterAdapter;
    SharedPreference sharedPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        sharedPreference = new SharedPreference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        findViewsById(view);

        setFavourites();

        favListAdapterAdapter = new FavListAdapter(activity, favourites);
        favListView.setAdapter(favListAdapterAdapter);
        favListView.setOnItemClickListener(this);
        favListView.setOnItemLongClickListener(this);
        return view;
    }

    private void setFavourites() {

        ActivityFavFraction product1 = new ActivityFavFraction(1, "Fracción 1");
        ActivityFavFraction product2 = new ActivityFavFraction(2, "Fracción 2");
        ActivityFavFraction product3 = new ActivityFavFraction(3, "Fracción 3");
        ActivityFavFraction product4 = new ActivityFavFraction(4, "Fracción 4");
        ActivityFavFraction product5 = new ActivityFavFraction(5, "Fracción 5");
        ActivityFavFraction product6 = new ActivityFavFraction(6, "Fracción 6");
        ActivityFavFraction product7 = new ActivityFavFraction(7, "Fracción 7");
        ActivityFavFraction product8 = new ActivityFavFraction(8, "Fracción 8");
        ActivityFavFraction product9 = new ActivityFavFraction(9, "Fracción 9");
        ActivityFavFraction product10 = new ActivityFavFraction(10, "Fracción 10");

        favourites = new ArrayList<ActivityFavFraction>();
        favourites.add(product1);
        favourites.add(product2);
        favourites.add(product3);
        favourites.add(product4);
        favourites.add(product5);
        favourites.add(product6);
        favourites.add(product7);
        favourites.add(product8);
        favourites.add(product9);
        favourites.add(product10);
    }

    private void findViewsById(View view) {
        favListView = (ListView) view.findViewById(R.id.list_product);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        ActivityFavFraction product = (ActivityFavFraction) parent.getItemAtPosition(position);
        Toast.makeText(activity, product.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                   int position, long arg3) {
        ImageView button = (ImageView) view.findViewById(R.id.imgbtn_favorite);

        String tag = button.getTag().toString();
        if (tag.equalsIgnoreCase("grey")) {
            sharedPreference.addFavorite(activity, favourites.get(position));
            Toast.makeText(activity,
                    activity.getResources().getString(R.string.add_favr),
                    Toast.LENGTH_SHORT).show();

            button.setTag("red");

        } else {
            sharedPreference.removeFavorite(activity, favourites.get(position));
            button.setTag("grey");

            Toast.makeText(activity,
                    activity.getResources().getString(R.string.remove_favr),
                    Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public void onResume() {
        getActivity().setTitle(R.string.fav_name);
        super.onResume();
    }
}