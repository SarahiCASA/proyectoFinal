package com.example.sistemascasa.tigie.presentador;

import android.content.Context;

import com.example.sistemascasa.tigie.fragments.IRecyclerview_Annexes;
import com.example.sistemascasa.tigie.pojo.Annexes;

import java.util.ArrayList;


public class RecyclerViewFragmentAnnexPre implements IRecyclerViewFragmentAnnexPre {


    private IRecyclerview_Annexes iRecyclerview_annexes;
    private Context context;
    private ArrayList<Annexes> annexes;


    public RecyclerViewFragmentAnnexPre(IRecyclerview_Annexes iRecyclerview_annexes, Context context, String fraccion) {
        this.iRecyclerview_annexes = iRecyclerview_annexes;
        this.context = context;
        getAnnexWS(fraccion);
    }

    @Override
    public void getAnnexWS(String fraccion) {

        annexes = new ArrayList<Annexes>();
        int tamanio =  6;

        for (int i = 0; i < tamanio ; i++) {

            int     idAnnexFraction         = 1;
            int     idTariffFraction        = 1;
            String  title                   = "IM (Mercancias sensibles IMMEX) - ECONOMIA - Importación";
            String  annexDescription        = "ANEXO II\n" +
                    "Mercancías que deberán cumplir requisitos específicos para poder importarse temporalmente al amparo del presente Decreto\n" +
                    "Las actividades bajo la modalidad de servicios que realicen las empresas con Programa, no podrán llevarse a cabo tratándose de mercancías que se indican en este Anexo, salvo las excepciones que establezca la Secretaría para empresas certificadas, así como para aquellas que cumplan los requisitos específicos que al efecto se determinen mediante Acuerdo.\n" +
                    "APARTADO C ";
            String  annexNoteDescription    = "3.3.1 Las disposiciones establecidas en el presente capítulo aplicarán a las empresas con Programa IMMEX que pretendan realizar la importación temporal y el R E T O R N O de las mercancías listadas en el Anexo II del Decreto IMMEX, así como de las señaladas en el Anexo 3.3.2 del presente ordenamiento, independientemente del bien final a producir.\n" +
                    "Fuente: Reglas y criterios en materia de comercio exterior, DOF: 12/12/2016 ";
            String  annexPublicationDate    = "06/01/2015 ";

            String  annexApplicationDate    = "Aplica su vigencia el: 31/01/2014";
            String  annexEffectiveDate      = "Termina su vigencia el: 31/01/2017";

            annexes.add(new Annexes(idAnnexFraction, idTariffFraction, title,  annexDescription, annexNoteDescription, annexPublicationDate, annexApplicationDate, annexEffectiveDate));
        }

        showAnnexDataRV();

    }

    @Override
    public void showAnnexDataRV() {
        iRecyclerview_annexes.inicializarAdaptadorRV(iRecyclerview_annexes.crearAdaptador(annexes));
        iRecyclerview_annexes.generarLinearLayoutVertical();
    }
}
