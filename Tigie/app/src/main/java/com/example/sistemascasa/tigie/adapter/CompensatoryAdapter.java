package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.CompensatoryShares;

import java.util.ArrayList;


public class CompensatoryAdapter  extends RecyclerView.Adapter<CompensatoryAdapter.CompensatoryViewHolder> {

    ArrayList<CompensatoryShares> compensatoryShares;
    Activity activity;


    public CompensatoryAdapter(ArrayList<CompensatoryShares> compensatoryShares, Activity activity) {
        this.compensatoryShares = compensatoryShares;
        this.activity = activity;
    }

    @Override
    public CompensatoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_compensatory, parent, false); //Cual sera el layout que estara reciclando la lista
        return new CompensatoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CompensatoryViewHolder compensatoryViewHolder, int position) {
        final CompensatoryShares compensatoryShare = compensatoryShares.get(position);

        compensatoryViewHolder.flagIconCompe.setImageResource(compensatoryShare.getCompensatoryFlag());
        compensatoryViewHolder.nameCountryCompe.setText(compensatoryShare.getCompensatoryShareCountry()); //Corea del sur
        compensatoryViewHolder.rateTextCompe.setText(compensatoryShare.getCompensatoryShareTypeDescription()); //Tasa provisional
        compensatoryViewHolder.rateCompe.setText(compensatoryShare.getCompensatoryShareTasa()); // 5.2
        compensatoryViewHolder.textCompe.setText(compensatoryShare.getCompensatoryShareMerchandiseDescription());  // aplica para
        compensatoryViewHolder.applicateCompen.setText(compensatoryShare.getCompensatoryShareAplicationDate()); //fecha app
        compensatoryViewHolder.dofCompen.setText(compensatoryShare.getCompensatorySharePublicationDate()); //fecha dof
        compensatoryViewHolder.effectiveCompen.setText(compensatoryShare.getCompensatoryShareEffectiveDate()); //fecha dof
    }

    @Override
    public int getItemCount() {
        return compensatoryShares.size();
    }


    /** Declara viesws
     * Metodo Constructor
     ***/
    public static class CompensatoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView flagIconCompe;
        private TextView nameCountryCompe;
        private TextView rateTextCompe;
        private TextView  rateCompe;
        private TextView  textCompe;
        private TextView  applicateCompen;
        private TextView  dofCompen;
        private TextView effectiveCompen;

        public CompensatoryViewHolder(View itemView) {
            super(itemView);
            flagIconCompe            = (ImageView) itemView.findViewById(R.id.flagIconCompe);
            nameCountryCompe         = (TextView)  itemView.findViewById(R.id.nameCountryCompe);
            rateTextCompe            = (TextView)  itemView.findViewById(R.id.rateTextCompe);
            rateCompe                = (TextView)  itemView.findViewById(R.id.rateCompe);
            textCompe                = (TextView)  itemView.findViewById(R.id.textCompe);
            applicateCompen          = (TextView)  itemView.findViewById(R.id.applicateCompen);
            dofCompen                = (TextView)  itemView.findViewById(R.id.dofCompen);
            effectiveCompen          = (TextView)  itemView.findViewById(R.id.effectiveCompen);
        }
    }
}
