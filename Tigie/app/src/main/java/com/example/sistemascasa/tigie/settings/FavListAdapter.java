package com.example.sistemascasa.tigie.settings;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;

import java.util.List;


public class FavListAdapter extends ArrayAdapter<ActivityFavFraction>{
    private Context context;
    List<ActivityFavFraction> favourites;
    SharedPreference sharedPreference;

    public FavListAdapter(Context context, List<ActivityFavFraction> products) {
        super(context, R.layout.list_favourites, products);
        this.context = context;
        this.favourites = products;
        sharedPreference = new SharedPreference();
    }

    private class ViewHolder {
        TextView productNameTxt;
        ImageView favoriteImg;
    }

    @Override
    public int getCount() {
        return favourites.size();
    }

    @Override
    public ActivityFavFraction getItem(int position) {
        return favourites.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_favourites, null);
            holder = new ViewHolder();
            holder.productNameTxt = (TextView) convertView.findViewById(R.id.txt_pdt_name);
            holder.favoriteImg = (ImageView) convertView.findViewById(R.id.imgbtn_favorite);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ActivityFavFraction product = (ActivityFavFraction) getItem(position);
        holder.productNameTxt.setText(product.getName());

        return convertView;
    }


    public boolean checkFavoriteItem(ActivityFavFraction checkProduct) {
        boolean check = false;
        List<ActivityFavFraction> favorites = sharedPreference.getFavorites(context);
        if (favorites != null) {
            for (ActivityFavFraction product : favorites) {
                if (product.equals(checkProduct)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    @Override
    public void add(ActivityFavFraction product) {
        super.add(product);
        favourites.add(product);
        notifyDataSetChanged();
    }

    @Override
    public void remove(ActivityFavFraction product) {
        super.remove(product);
        favourites.remove(product);
        notifyDataSetChanged();
    }
}
