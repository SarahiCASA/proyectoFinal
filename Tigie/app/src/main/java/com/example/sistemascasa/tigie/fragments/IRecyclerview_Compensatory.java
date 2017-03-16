package com.example.sistemascasa.tigie.fragments;

import com.example.sistemascasa.tigie.adapter.CompensatoryAdapter;
import com.example.sistemascasa.tigie.pojo.CompensatoryShares;

import java.util.ArrayList;


public interface IRecyclerview_Compensatory {

    public CompensatoryAdapter crearAdaptador(ArrayList<CompensatoryShares> compensatoryShares);

    public void inicializarAdaptadorRV (CompensatoryAdapter adapter); //Recibiendo Adaptador

    public void generarGridLayout();

}
