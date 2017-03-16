package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.SubheadingsActivity;
import com.example.sistemascasa.tigie.pojo.Chapters;
import com.example.sistemascasa.tigie.pojo.Headings;

import java.util.ArrayList;

public class HeadingAdapter extends RecyclerView.Adapter<HeadingAdapter.HeadingViewHolder> {

    ArrayList<Headings> headings;
    Activity activity;
    Chapters chapters = new Chapters();
    public HeadingAdapter(ArrayList<Headings> headings, Activity activity) {
        this.headings = headings;
        this.activity = activity;
    }

    @Override
    public HeadingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_headings, parent, false); //Cual sera el layout que estara reciclando la lista
        return new HeadingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HeadingViewHolder headingViewHolder, int position) {
        final Headings heading = headings.get(position);

        headingViewHolder.idTariffHeading.setText(String.valueOf(heading.getIdTariffHeading()));
        headingViewHolder.idTariffChapter.setText(String.valueOf(heading.getIdTariffChapter()));
        headingViewHolder.headingCode.setText(heading.getTariffHeadingCode());
        headingViewHolder.tariffHeadingDescription.setText(heading.getTariffHeadingDescription());
        headingViewHolder.tariffHeadingIcon.setImageResource(heading.getTariffHeadingIcon());

        headingViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentSubheading = new Intent(activity, SubheadingsActivity.class);

                intentSubheading.putExtra("iconChapter","2130837583");
                intentSubheading.putExtra("idTariffHeading", String.valueOf(heading.getIdTariffHeading()));
                intentSubheading.putExtra("tariffHeadingCode", heading.getTariffHeadingCode());
                intentSubheading.putExtra("tariffHeadingDescription", heading.getTariffHeadingDescription());
                intentSubheading.putExtra("idHeading", heading.getIdTariffHeading());

                activity.startActivity(intentSubheading);

            }
        });

    }

    @Override
    public int getItemCount() {
        return headings.size();
    }

    public static class HeadingViewHolder extends RecyclerView.ViewHolder {

        private ImageView tariffHeadingIcon;
        private TextView idTariffHeading;
        private TextView idTariffChapter;
        private TextView headingCode;
        private TextView tariffHeadingDescription;

        public HeadingViewHolder(View itemView) {

            super(itemView);

            idTariffHeading            = (TextView)  itemView.findViewById(R.id.tvIdTariffHeading);
            idTariffChapter            = (TextView)  itemView.findViewById(R.id.tvIdTariffChapter);
            headingCode                = (TextView)  itemView.findViewById(R.id.tvHeadingCode);
            tariffHeadingDescription   = (TextView)  itemView.findViewById(R.id.tvHeadingDescription);
            tariffHeadingIcon          = (ImageView) itemView.findViewById(R.id.ivHeadingIcon);
        }
    }
}