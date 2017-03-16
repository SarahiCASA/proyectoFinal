package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.FragmentsActivity.FractionInformationActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.Fractions;

import java.util.ArrayList;


public class FractionFavAdapater extends RecyclerView.Adapter<FractionFavAdapater.FractionFavViewHolder> {

    ArrayList<Fractions> fractions;
    Activity activity;

    public FractionFavAdapater(ArrayList<Fractions> fractions, Activity activity) {
        this.fractions = fractions;
        this.activity = activity;
    }

    @Override
    public FractionFavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_favourites, parent, false);
        return new FractionFavViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FractionFavViewHolder fractionFavViewHolder, int position) {
        final Fractions fraction = fractions.get(position);

        fractionFavViewHolder.idTariffFraction.setText(String.valueOf(fraction.getIdTariffFraction()));
        fractionFavViewHolder.tariffFractionCode.setText(fraction.getTariffFractionCode());
        fractionFavViewHolder.tariffFractionDescription.setText(fraction.getTariffFractionDescription());

        fractionFavViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FractionInformationActivity.class);
                intent.putExtra("fractionCode", fraction.getTariffFractionCode());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return fractions.size();
    }

    public static class FractionFavViewHolder extends RecyclerView.ViewHolder {


        private TextView idTariffFraction;
        private TextView tariffFractionCode;
        private TextView tariffFractionDescription;

        public FractionFavViewHolder(View itemView) {

            super(itemView);

            idTariffFraction                = (TextView)  itemView.findViewById(R.id.id_favourite);
            tariffFractionCode              = (TextView)  itemView.findViewById(R.id.tariffFractionCodeFavourite);
            tariffFractionDescription       = (TextView)  itemView.findViewById(R.id.tariffFractionFavouriteDesc);
        }
    }
}
