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
import com.example.sistemascasa.tigie.adapter.BorderStripAdapter;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_BorderStrip;
import com.example.sistemascasa.tigie.pojo.BorderStrip;
import com.example.sistemascasa.tigie.presentador.IRecyclerViewFragmentBorderPre;
import com.example.sistemascasa.tigie.presentador.RecyclerViewFragmentBorderPre;

import java.util.ArrayList;


public class BorderstripFragment extends Fragment implements IRecyclerview_BorderStrip {
    private Context context;
    private RecyclerView listBorder;
    private IRecyclerViewFragmentBorderPre presenter;
    Comunicador comunicador;
    TextView tvFraccionBorderCode;

    public BorderstripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_borderstrip, container, false);

        comunicador     = (Comunicador) getActivity();
        TextView fracc  = (TextView) getActivity().findViewById(R.id.tvFraccion);
        String fraccion = fracc.getText().toString();

        tvFraccionBorderCode = (TextView) v.findViewById(R.id.tvFraccionBorderCode);
        tvFraccionBorderCode.setText(fraccion);

        listBorder = (RecyclerView) v.findViewById(R.id.rvBorder);
        presenter = new RecyclerViewFragmentBorderPre(this, getContext(), fraccion);
        return v;

    }

    @Override
    public BorderStripAdapter crearAdaptador(ArrayList<BorderStrip> borderStrips) {
        BorderStripAdapter adapter = new BorderStripAdapter(borderStrips, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(BorderStripAdapter adapter) {
        listBorder.setAdapter(adapter);
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listBorder.setLayoutManager(llm);
    }
}
