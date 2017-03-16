package com.example.sistemascasa.tigie.presentador;

import android.content.Context;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Compensatory;
import com.example.sistemascasa.tigie.pojo.CompensatoryShares;
import com.example.sistemascasa.tigie.rest.IconosBandera;

import java.util.ArrayList;

public class RecyclerViewFragmentCompenPre implements IRecyclerViewFragmentCompensatoryPre {

    private final IRecyclerview_Compensatory iRecyclerview_compensatory;
    private Context             context;
    private ConstructorData constructorData;
    private ArrayList<CompensatoryShares> compensatoryShares;

    public RecyclerViewFragmentCompenPre(IRecyclerview_Compensatory iRecyclerview_compensatory, Context context, String fraccion) {

        this.iRecyclerview_compensatory = iRecyclerview_compensatory;
        this.context = context;
        getCompensatoryWS(fraccion);
    }

    @Override
    public void getCompensatoryBD() {
        constructorData = new ConstructorData(context);
        showCompensatoryDataRV();
    }

    @Override
    public void getCompensatoryWS(String fraccion) {

        final IconosBandera iconosBandera = new IconosBandera();
        compensatoryShares = new ArrayList<CompensatoryShares>();

        int tamanio =  5;

        for (int i = 0; i < tamanio ; i++) {

            int compensatoryFlag         = R.drawable.z_coreadelsur;

            int idCompensatoryShare         = 1;
            int idTariffFraction            = 1;
            String compensatoryShareFlag    = "Corea del Sur";
            String compensatoryShareCountry = "Corea del Sur";
            String compensatoryShareTasa    = "60.4 %";
            String compensatoryShareTypeDescription = "Tasa Provisional";
            String compensatoryShareMerchandiseDescription = "Lámina de acero rolada en frío cruda o recocida, tanto aleada como sin alear, con un contenido de boro igual o superior a 0.0008%; sin chapar ni revestir, de ancho igual o superior a 600 mm y de espesor inferior a 3 mm. B. Para las importaciones provenientes de POSCO, una cuota compensatoria provisional de 60.40%. y \n" +
                    "C. Para el resto de las exportadoras, una cuota compensatoria provisional de 60.40%.";
            String compensatorySharePublicationDate = "02/02/2016";
            String compensatoryShareAplicationDate  = "Aplica a partir de: 04/06/2013";
            String compensatoryShareEffectiveDate   = "Termina a partir de: 04/06/2017";

            compensatoryShares.add(new CompensatoryShares( idCompensatoryShare,  idTariffFraction,  compensatoryShareFlag,  compensatoryShareCountry,
                    compensatoryShareTasa,  compensatoryShareTypeDescription,  compensatoryShareMerchandiseDescription,
                    compensatorySharePublicationDate,  compensatoryShareAplicationDate,
                    compensatoryShareEffectiveDate, compensatoryFlag));

        }

        showCompensatoryDataRV();

    }

    @Override
    public void showCompensatoryDataRV() {
        iRecyclerview_compensatory.inicializarAdaptadorRV(iRecyclerview_compensatory.crearAdaptador(compensatoryShares));
        iRecyclerview_compensatory.generarGridLayout();
    }
}
