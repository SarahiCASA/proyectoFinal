package com.example.sistemascasa.tigie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.pojo.Chapters;
import com.example.sistemascasa.tigie.pojo.Headings;
import com.example.sistemascasa.tigie.pojo.Subheadings;

/**
 * Created by desarrolloweb on 23/08/16.
 */
public class Comunes {

    private static ConstructorData constructorData;
    private static Context context;
    private static Activity activity;

    private static Chapters chapters;
    private Headings headings;
    private Subheadings subheadings;


    public static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }


    public static void getChapters(String chapterCode) {

        constructorData = new ConstructorData(context);
        chapters = constructorData.getChaptersInf(chapterCode);

        if (chapters.getId() != 0) {
            Intent intent_head = new Intent(activity, HeadingsActivity.class);
            intent_head.putExtra("iconChapter", chapters.getIcono());
            intent_head.putExtra("idTariffChapter", String.valueOf(chapters.getId()));
            intent_head.putExtra("tariffChapterCode", chapters.getCodigo());
            intent_head.putExtra("tariffChapterDescription", chapters.getDescripcion());
            intent_head.putExtra("idChapter", chapters.getId());
            activity.startActivity(intent_head);
        } else {

            String text = "ERROR! La Búsqueda fue incorrecta.";
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
        }

    }

}
