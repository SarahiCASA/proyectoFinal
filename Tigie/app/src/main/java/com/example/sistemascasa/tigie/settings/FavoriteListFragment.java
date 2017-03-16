package com.example.sistemascasa.tigie.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.List;


public class FavoriteListFragment extends Fragment {

    public static final String ARG_ITEM_ID = "favorite_list";

    ListView favoriteList;
    SharedPreference sharedPreference;
    List<ActivityFavFraction> favorites;
    Activity activity;
    FavListAdapter favListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_favourites, container, false);

        sharedPreference = new SharedPreference();
        favorites = sharedPreference.getFavorites(activity);

        if (favorites == null) {
            showAlert(getResources().getString(R.string.no_favorites_items), getResources().getString(R.string.no_favorites_msg));
        } else {

            if (favorites.size() == 0) {
                showAlert( getResources().getString(R.string.no_favorites_items), getResources().getString(R.string.no_favorites_msg));
            }

            favoriteList = (ListView) view.findViewById(R.id.list_product);
            if (favorites != null) {
                favListAdapter = new FavListAdapter(activity, favorites);
                favoriteList.setAdapter(favListAdapter);

                favoriteList.setOnItemClickListener(new OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {

                    }
                });

                favoriteList.setOnItemLongClickListener(new OnItemLongClickListener() {

                            @Override
                            public boolean onItemLongClick(
                                    AdapterView<?> parent, View view,
                                    int position, long id) {

                        ImageView button = (ImageView) view.findViewById(R.id.imgbtn_favorite);

                        String tag = button.getTag().toString();
                        if (tag.equalsIgnoreCase("grey")) {
                            sharedPreference.addFavorite(activity, favorites.get(position));
                            Toast.makeText( activity, activity.getResources().getString(
                                            R.string.add_favr), Toast.LENGTH_SHORT).show();

                            button.setTag("red");
                        } else {
                            sharedPreference.removeFavorite(activity, favorites.get(position));
                            button.setTag("grey");
                            favListAdapter.remove(favorites.get(position));
                            Toast.makeText( activity, activity.getResources().getString(
                                            R.string.remove_favr), Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
            }
        }
        return view;
    }

    public void showAlert(String title, String message) {
        if (activity != null && !activity.isFinishing()) {
            AlertDialog alertDialog = new AlertDialog.Builder(activity)
                    .create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            getFragmentManager().popBackStackImmediate();
                        }
            });
            alertDialog.show();
        }
    }

    @Override
    public void onResume() {
        getActivity().setTitle(R.string.favorites);
        getActivity().getActionBar().setTitle(R.string.favorites);
        super.onResume();
    }
}