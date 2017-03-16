package com.example.sistemascasa.tigie.FragmentsActivity;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.adapter.AnnexesAdapter;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Annexes;
import com.example.sistemascasa.tigie.pojo.Annexes;
import com.example.sistemascasa.tigie.presentador.IRecyclerViewFragmentAnnexPre;
import com.example.sistemascasa.tigie.presentador.RecyclerViewFragmentAnnexPre;

import java.util.ArrayList;


public class AnnexFragment extends Fragment implements IRecyclerview_Annexes {

    private Context context;
    private RecyclerView listAnnex;
    private IRecyclerViewFragmentAnnexPre presenter;
    Comunicador comunicador;
    TextView tvFraccionAnnexCode;

    public AnnexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_annex, container, false);;

        comunicador     = (Comunicador) getActivity();
        TextView fracc  = (TextView) getActivity().findViewById(R.id.tvFraccion);
        String fraccion = fracc.getText().toString();

        tvFraccionAnnexCode = (TextView) v.findViewById(R.id.tvFraccionAnnexCode);
        tvFraccionAnnexCode.setText(fraccion);

        listAnnex = (RecyclerView) v.findViewById(R.id.rvAnnex);
        presenter = new RecyclerViewFragmentAnnexPre(this, getContext(), fraccion);

        return v;
    }

    @Override
    public AnnexesAdapter crearAdaptador(ArrayList<Annexes> annexes) {
        AnnexesAdapter adapter = new AnnexesAdapter(annexes, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(AnnexesAdapter adapter) {
        listAnnex.setAdapter(adapter);
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listAnnex.setLayoutManager(llm);
    }
}
