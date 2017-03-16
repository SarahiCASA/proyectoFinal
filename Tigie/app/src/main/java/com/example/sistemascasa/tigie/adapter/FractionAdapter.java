package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TintTypedArray;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.FragmentsActivity.FractionInformationActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.Fractions;

import java.util.ArrayList;


public class FractionAdapter extends RecyclerView.Adapter<FractionAdapter.FractionViewHolder>{

    ArrayList<Fractions> fractions;
    Activity activity;
    String text;

    public FractionAdapter(ArrayList<Fractions> fractions, Activity activity, String text) {
        this.fractions = fractions;
        this.activity  = activity;
        this.text      = text;
    }

    @Override
    public FractionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_fractions, parent, false);
        return new FractionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FractionViewHolder fractionViewHolder, int position) {
        final Fractions fraction = fractions.get(position);

        if(!text.equals("")) {

            String sTexto   =  fraction.getTariffFractionDescription().toString().toLowerCase();
            int longitud    =  text.length();
            int start       =  sTexto.indexOf(text.toLowerCase());

            Spannable wordtoSpan = new SpannableString(fraction.getTariffFractionDescription().toString());
            System.out.println(start);

            if(start != -1){
                wordtoSpan.setSpan(new BackgroundColorSpan(Color.parseColor("#f9cf4f")), start, start + longitud, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                fractionViewHolder.tariffFractionDescription.setText(wordtoSpan);
            } else
                fractionViewHolder.tariffFractionDescription.setText(fraction.getTariffFractionDescription());

        } else {
            fractionViewHolder.tariffFractionDescription.setText(fraction.getTariffFractionDescription());
        }

        fractionViewHolder.idTariffFraction.setText(String.valueOf(fraction.getIdTariffFraction()));
        fractionViewHolder.idTariffSubheadingFrac.setText(String.valueOf(fraction.getIdTariffSubheading()));
        fractionViewHolder.tariffFractionCode.setText(fraction.getTariffFractionCode());
        fractionViewHolder.tariffFractionIcon.setImageResource(fraction.getTariffFractionIcon());


        fractionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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




    public static class FractionViewHolder extends RecyclerView.ViewHolder {

        private ImageView tariffFractionIcon;
        private TextView idTariffFraction;
        private TextView idTariffSubheadingFrac;
        private TextView tariffFractionCode;
        private TextView tariffFractionDescription;

        public FractionViewHolder(View itemView) {

            super(itemView);

            idTariffFraction                = (TextView)  itemView.findViewById(R.id.idTariffFraction);
            idTariffSubheadingFrac          = (TextView)  itemView.findViewById(R.id.idTariffSubheadingFrac);
            tariffFractionCode              = (TextView)  itemView.findViewById(R.id.tvtariffFractionCode);
            tariffFractionDescription       = (TextView)  itemView.findViewById(R.id.tariffFractionDescription);
            tariffFractionIcon              = (ImageView) itemView.findViewById(R.id.tariffFractionIcon);
        }
    }
}
