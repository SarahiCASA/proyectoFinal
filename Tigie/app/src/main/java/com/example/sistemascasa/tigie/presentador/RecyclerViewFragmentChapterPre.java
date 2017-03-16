package com.example.sistemascasa.tigie.presentador;

import android.content.Context;

import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Fragment;
import com.example.sistemascasa.tigie.pojo.Chapters;

import java.util.ArrayList;

public class RecyclerViewFragmentChapterPre implements IRecyclerViewFragmentChapterPre {

    private IRecyclerview_Fragment iRecyclerview_Fragment;
    private Context context;
    private ConstructorData constructorData;
    private ArrayList<Chapters> chapters;

    public RecyclerViewFragmentChapterPre(IRecyclerview_Fragment iRecyclerview_Fragment, Context context) {
        this.iRecyclerview_Fragment = iRecyclerview_Fragment;
        this.context = context;
        getChaptersBD();
    }

    @Override
    public void getChaptersBD() {
        constructorData = new ConstructorData(context);
        chapters = constructorData.getChapters(); //Separacion de datos de la fuente para la presentacion
        showChapterDataRV();
    }

    @Override
    public void showChapterDataRV() {
        iRecyclerview_Fragment.inicializarAdaptadorRV(iRecyclerview_Fragment.crearAdaptador(chapters));
        iRecyclerview_Fragment.generarLinearLayoutVertical();
    }
}
