package com.example.sistemascasa.tigie.fragments;

import com.example.sistemascasa.tigie.adapter.FractionFavAdapater;
import com.example.sistemascasa.tigie.pojo.Fractions;

import java.util.ArrayList;

public interface IRecyclerview_Fractions_Fav {

    public void generarLinearLayoutVertical();

    public FractionFavAdapater crearAdaptador(ArrayList<Fractions> fractionses);

    public void inicializarAdaptadorRV (FractionFavAdapater adapter); //Recibiendo Adaptador

}
