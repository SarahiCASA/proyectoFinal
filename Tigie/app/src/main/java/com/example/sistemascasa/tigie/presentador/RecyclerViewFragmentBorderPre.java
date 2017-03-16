package com.example.sistemascasa.tigie.presentador;

import android.content.Context;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_BorderStrip;
import com.example.sistemascasa.tigie.pojo.BorderStrip;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.adapter.RestApiAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecyclerViewFragmentBorderPre implements IRecyclerViewFragmentBorderPre {

    private IRecyclerview_BorderStrip iRecyclerview_borderStrip;
    private Context context;
    private ArrayList<BorderStrip> borderStrips;


    public RecyclerViewFragmentBorderPre(IRecyclerview_BorderStrip iRecyclerview_borderStrip, Context context, String fraccion) {

        this.iRecyclerview_borderStrip = iRecyclerview_borderStrip;
        this.context = context;
        getBorderWS(fraccion);
    }

    @Override
    public void getBorderWS(String fraccion) {

        borderStrips = new ArrayList<BorderStrip>();

        int tamanio =  5;

        for (int i = 0; i < tamanio ; i++) {
            int     idBorderStripPreference     = 1;
            int     idTariffFraction            = 1;
            String  borderStripAccordanceTariffRate     = "74%";
            String  borderStripAccordanceDescription    = "ARTÍCULO 5.- Las mercancías que se importen por las personas que cuenten con registro como empresa de la frontera a la franja fronteriza norte y a la región fronteriza estarán total o parcialmente desgravadas del impuesto general de importación, en los siguientes términos:\n" +
                    "\n" +
                    "I. Las mercancías identificadas en las fracciones arancelarias de la Tarifa de la Ley de los Impuestos Generales de Importación y de Exportación que a continuación se indican, estarán totalmente desgravadas hasta el 31 de diciembre de 2019:\n" +
                    "D.O.F.: 17/nov/2016";
            String  borderStripPreferenceAgreementDate  = "Aplica apartir 12/01/2016";
            String  borderEffectiveDate                 = "Termina apartir 12/01/2018";

            borderStrips.add(new BorderStrip(idBorderStripPreference, idTariffFraction, borderStripAccordanceTariffRate, borderStripAccordanceDescription, borderStripPreferenceAgreementDate, borderEffectiveDate ));

        }

        showBorderDataRV();

    }

    @Override
    public void showBorderDataRV() {
        iRecyclerview_borderStrip.inicializarAdaptadorRV(iRecyclerview_borderStrip.crearAdaptador(borderStrips));
        iRecyclerview_borderStrip.generarLinearLayoutVertical();
    }
}
