package com.example.sistemascasa.tigie.fragments;

import com.example.sistemascasa.tigie.adapter.ChapterAdapter;
import com.example.sistemascasa.tigie.pojo.Chapters;

import java.util.ArrayList;

public interface IRecyclerview_Fragment {

    public void generarLinearLayoutVertical();

    public ChapterAdapter crearAdaptador(ArrayList<Chapters> chapters);

    public void inicializarAdaptadorRV (ChapterAdapter adapter); //Recibiendo Adaptador

    public void generarGridLayout();


}
