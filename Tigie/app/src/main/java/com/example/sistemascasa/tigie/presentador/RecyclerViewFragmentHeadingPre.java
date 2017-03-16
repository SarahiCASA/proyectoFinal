package com.example.sistemascasa.tigie.presentador;

import android.content.Context;

import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Heading;
import com.example.sistemascasa.tigie.pojo.Headings;

import java.util.ArrayList;

public class RecyclerViewFragmentHeadingPre implements IRecyclerViewFragmentHeadingPre {

    private IRecyclerview_Heading iRecyclerview_heading;
    private Context context;
    private ConstructorData constructorData;
    private ArrayList<Headings> headingses;

    public RecyclerViewFragmentHeadingPre(IRecyclerview_Heading iRecyclerview_heading, Context context, int idTariffChapterVista) {

        this.iRecyclerview_heading = iRecyclerview_heading;
        this.context = context;
        getHeadingssBD(idTariffChapterVista);

    }

    @Override
    public void getHeadingssBD(int idTariffChapterVista) {
        constructorData = new ConstructorData(context);
        headingses = constructorData.getHeadings(idTariffChapterVista); //Separacion de datos de la fuente para la presentacion
        showHeadingDataRV();
    }

    @Override
    public void showHeadingDataRV() {
        iRecyclerview_heading.inicializarAdaptadorRV(iRecyclerview_heading.crearAdaptador(headingses));
        iRecyclerview_heading.generarLinearLayoutVertical();

    }
}
