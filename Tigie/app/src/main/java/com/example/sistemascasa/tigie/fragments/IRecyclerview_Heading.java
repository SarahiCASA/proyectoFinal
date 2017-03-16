package com.example.sistemascasa.tigie.fragments;


import com.example.sistemascasa.tigie.adapter.HeadingAdapter;
import com.example.sistemascasa.tigie.pojo.Headings;

import java.util.ArrayList;

public interface IRecyclerview_Heading {

    public void generarLinearLayoutVertical();

    public HeadingAdapter crearAdaptador(ArrayList<Headings> headingses);

    public void inicializarAdaptadorRV (HeadingAdapter adapter); //Recibiendo Adaptador

}
