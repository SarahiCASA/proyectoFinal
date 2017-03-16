package com.example.sistemascasa.tigie.presentador;

import android.content.Context;

import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_SubHeading;
import com.example.sistemascasa.tigie.pojo.Subheadings;

import java.util.ArrayList;

public class RecyclerViewFragmentSubheadingPre implements  IRecyclerViewFragmentSubheadingPre {

    private IRecyclerview_SubHeading iRecyclerview_subHeading;
    private Context context;
    private ArrayList<Subheadings> subheadingses;
    private ConstructorData constructorData;

    public RecyclerViewFragmentSubheadingPre(IRecyclerview_SubHeading iRecyclerview_subHeading, Context context, int idTariffHeading) {

        this.iRecyclerview_subHeading = iRecyclerview_subHeading;
        this.context = context;
        getSubHeadingsBD(idTariffHeading);

    }


    @Override
    public void getSubHeadingsBD(int idTariffHeading) {
        constructorData = new ConstructorData(context);
        subheadingses = constructorData.getSubHeadings(idTariffHeading); //Separacion de datos de la fuente para la presentacion
        showSubHeadingDataRV();

    }

    @Override
    public void showSubHeadingDataRV() {
        iRecyclerview_subHeading.inicializarAdaptadorRV(iRecyclerview_subHeading.crearAdaptador(subheadingses));
        iRecyclerview_subHeading.generarLinearLayoutVertical();

    }
}
