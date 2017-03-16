package com.example.sistemascasa.tigie.fragments;

import com.example.sistemascasa.tigie.adapter.BorderStripAdapter;
import com.example.sistemascasa.tigie.pojo.BorderStrip;

import java.util.ArrayList;

public interface IRecyclerview_BorderStrip {

    public BorderStripAdapter crearAdaptador(ArrayList<BorderStrip> borderStrips);

    public void inicializarAdaptadorRV (BorderStripAdapter adapter); //Recibiendo Adaptador

    public void generarLinearLayoutVertical();
}
