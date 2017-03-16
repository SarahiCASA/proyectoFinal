package com.example.sistemascasa.tigie.fragments;

import com.example.sistemascasa.tigie.adapter.FractionAdapter;
import com.example.sistemascasa.tigie.adapter.HeadingAdapter;
import com.example.sistemascasa.tigie.pojo.Fractions;
import com.example.sistemascasa.tigie.pojo.Headings;

import java.util.ArrayList;

public interface IRecyclerview_Fractions {

    public void generarLinearLayoutVertical();

    public FractionAdapter crearAdaptador(ArrayList<Fractions> fractionses);

    public void inicializarAdaptadorRV (FractionAdapter adapter); //Recibiendo Adaptador

}
