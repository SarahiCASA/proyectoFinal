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
import com.example.sistemascasa.tigie.adapter.CompensatoryAdapter;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Compensatory;
import com.example.sistemascasa.tigie.pojo.CompensatoryShares;
import com.example.sistemascasa.tigie.presentador.IRecyclerViewFragmentCompensatoryPre;
import com.example.sistemascasa.tigie.presentador.RecyclerViewFragmentCompenPre;

import java.util.ArrayList;

public class CompensatoryFragment  extends Fragment implements IRecyclerview_Compensatory {

    private Context context;
    private RecyclerView listCompensatory;
    private IRecyclerViewFragmentCompensatoryPre presenter;

    TextView tvFraccionCompensatoryCode;
    Comunicador comunicador;

    public CompensatoryFragment () { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_compensatory, container, false);

        comunicador     = (Comunicador) getActivity();
        TextView fracc  = (TextView) getActivity().findViewById(R.id.tvFraccion);
        String fraccion = fracc.getText().toString();

        tvFraccionCompensatoryCode = (TextView) v.findViewById(R.id.tvFraccionCompensatoryCode);
        tvFraccionCompensatoryCode.setText(fraccion);

        listCompensatory = (RecyclerView) v.findViewById(R.id.rvCompensatory);
        presenter = new RecyclerViewFragmentCompenPre(this, getContext(), fraccion);
        return v;
    }

    @Override
    public CompensatoryAdapter crearAdaptador(ArrayList<CompensatoryShares> compensatoryShares) {
        CompensatoryAdapter adapter = new CompensatoryAdapter(compensatoryShares, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(CompensatoryAdapter adapter) {
        listCompensatory.setAdapter(adapter);
    }

    @Override
    public void generarGridLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listCompensatory.setLayoutManager(llm);
    }
}
