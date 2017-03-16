package com.example.sistemascasa.tigie.fragments;


import com.example.sistemascasa.tigie.adapter.AnnexesAdapter;
import com.example.sistemascasa.tigie.pojo.Annexes;

import java.util.ArrayList;

public interface IRecyclerview_Annexes {

    public AnnexesAdapter crearAdaptador(ArrayList<Annexes> annexes);

    public void inicializarAdaptadorRV (AnnexesAdapter adapter); //Recibiendo Adaptador

    public void generarLinearLayoutVertical();
}
