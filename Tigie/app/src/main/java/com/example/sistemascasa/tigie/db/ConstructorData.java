package com.example.sistemascasa.tigie.db;

import android.content.Context;
import android.database.Cursor;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.Chapters;
import com.example.sistemascasa.tigie.pojo.Fractions;
import com.example.sistemascasa.tigie.pojo.Headings;
import com.example.sistemascasa.tigie.pojo.Subheadings;
import java.util.ArrayList;

public class ConstructorData {


    public void startDb () {
        BaseDatos db = new BaseDatos(context);

        TestAdapter mDbHelper = new TestAdapter(context);
        mDbHelper.createDatabase();
        mDbHelper.open();
        Cursor testdata = mDbHelper.getTestData();
        mDbHelper.close();
    }

    private Context context;
    public ConstructorData (Context context){
        this.context = context;
    }

    ArrayList<Fractions> fractionses;

    public ArrayList<Chapters> getChapters ()  {

        BaseDatos db = new BaseDatos(context);
        return db.getAllChapters();

    }

    public ArrayList<Headings> getHeadings (int idTariffChapterVista) {
        BaseDatos db1 = new BaseDatos(this.context);
        return db1.getHeadingsBD(idTariffChapterVista);
    }

    public ArrayList<Subheadings> getSubHeadings (int idTariffHeading) {
        BaseDatos db1 = new BaseDatos(this.context);
        return db1.getSubHeadingsBD(idTariffHeading);
    }

    public ArrayList<Fractions> getFractions (int idTariffSubheading, String text) {

        BaseDatos db1 = new BaseDatos(this.context);

        if(idTariffSubheading == 0) {
            return db1.getFractionsInf(text);
        } else {
            return db1.getFractionsBD(idTariffSubheading);
        }

    }



    public Chapters getChaptersInf (String CodeChapter) {
        BaseDatos db = new BaseDatos(context);
        return db.getChaptersInf(CodeChapter);
    }


    public Headings getHeadingsInf(String headingCode) {
        BaseDatos db = new BaseDatos(context);
        return db.getHeadingsInf(headingCode);
    }

    public Subheadings getSubheadingsInf(String subheadingCode) {
        BaseDatos db = new BaseDatos(context);
        return db.getSubheadingsInf(subheadingCode);
    }

    public Boolean insertFavouites (String fraction) {
        BaseDatos db = new BaseDatos(context);
        Boolean validate = db.insertFavouites(fraction);

        if(validate)
            return true;
        else
            return false;
    }

    public Boolean compareUser (String alias, String pass){
        BaseDatos db = new BaseDatos(context);
        Boolean response = db.compareUser(alias, pass);
        return response;
    }

    public ArrayList<Fractions> getFractionsFav () {
        BaseDatos db = new BaseDatos(context);
        return db.getFavourites();
    }

    public Boolean insertUserData (String email, String password, int status){
        BaseDatos bd = new BaseDatos(this.context);
        return bd.insertUserData(email, password, status);
    }
}
