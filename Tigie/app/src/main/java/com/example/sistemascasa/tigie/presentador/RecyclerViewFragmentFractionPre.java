package com.example.sistemascasa.tigie.presentador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Fractions;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Heading;
import com.example.sistemascasa.tigie.pojo.Fractions;
import com.example.sistemascasa.tigie.pojo.Headings;
import com.example.sistemascasa.tigie.pojo.Subheadings;

import java.util.ArrayList;

public class RecyclerViewFragmentFractionPre  implements IRecyclerViewFragmentFractionPre {

    private IRecyclerview_Fractions iRecyclerview_fractions;
    private Context context;
    private ConstructorData constructorData;
    private ArrayList<Fractions> fractions;

    public RecyclerViewFragmentFractionPre(IRecyclerview_Fractions iRecyclerview_fractions, Context context, int idTariffSubheading, String text) {

        this.iRecyclerview_fractions = iRecyclerview_fractions;
        this.context = context;
        getFractionsBD(idTariffSubheading, text);

    }

    @Override
    public void getFractionsBD(int idTariffSubheading, String text) {
        constructorData = new ConstructorData(context);
        fractions = constructorData.getFractions(idTariffSubheading, text);
        showFractionDataRV();

    }

    @Override
    public void showFractionDataRV() {
        iRecyclerview_fractions.inicializarAdaptadorRV(iRecyclerview_fractions.crearAdaptador(fractions));
        iRecyclerview_fractions.generarLinearLayoutVertical();
    }
}
