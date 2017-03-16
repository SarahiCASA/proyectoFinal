package com.example.sistemascasa.tigie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.Chapters;
import com.example.sistemascasa.tigie.pojo.Fractions;
import com.example.sistemascasa.tigie.pojo.Headings;
import com.example.sistemascasa.tigie.pojo.Subheadings;


import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;


    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    public void open() throws SQLException {
        close();
        this.getWritableDatabase();
    }

    public void closeDB() {

        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Chapters> getAllChapters() {
        ArrayList<Chapters> chapters = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_TARIFF_CHAPTERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);   //Devolver coleccion de datos del query que consulto  "null" es para definir parametros
        int contador = 1;

        while (registros.moveToNext()) {

            if (contador <= 98) {
                Chapters chapterActual = new Chapters();

                chapterActual.setId(registros.getInt(0));
                chapterActual.setCodigo(registros.getString(1));
                chapterActual.setDescripcion(registros.getString(2));
                chapterActual.setIcono(registros.getInt(3));

                chapters.add(chapterActual);
            }
            contador++;

        }

        db.close();

        return chapters;
    }

    public ArrayList<Headings> getHeadingsBD(int idTariffChapterVista) {
        ArrayList<Headings> headings = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_TARIFF_HEADINGS +
                " WHERE " + ConstantesBaseDatos.TABLE_TARIFF_HEADINGS_CHAPTERS_ID + " = " + idTariffChapterVista;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);   //Devolver coleccion de datos del query que consulto  "null" es para definir parametros

        while (registros.moveToNext()) {
            Headings headingActual = new Headings();

            headingActual.setIdTariffHeading(registros.getInt(0));
            headingActual.setIdTariffChapter(registros.getInt(1));
            headingActual.setTariffHeadingCode(registros.getString(2));
            headingActual.setTariffHeadingDescription(registros.getString(3));
            headingActual.setTariffHeadingIcon(registros.getInt(4));
            headings.add(headingActual);
        }

        db.close();

        return headings;
    }

    public ArrayList<Subheadings> getSubHeadingsBD(int idTariffHeading) {
        ArrayList<Subheadings> subheadingses = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_TARIFF_SUBHEADINGS +
                " WHERE " + ConstantesBaseDatos.TABLE_TARIFF_SUBHEADINGS_HEADING_ID + " = " + idTariffHeading;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            Subheadings subheadingActual = new Subheadings();

            if (registros.getString(4) != null) {
                if (registros.getInt(1) != 1) {

                    String queryDescr = "SELECT " + ConstantesBaseDatos.TABLE_TARIFF_PRESUBHEADINGS_DESCRIP + " FROM " + ConstantesBaseDatos.TABLE_TARIFF_PRESUBHEADINGS +
                            " WHERE " + ConstantesBaseDatos.TABLE_TARIFF_PRESUBHEADINGS_PRESUBHEADINGS_ID + " = " + registros.getInt(1);
                    Cursor registros2 = db.rawQuery(queryDescr, null);

                    while (registros2.moveToNext()) {
                        String descripcion = registros2.getString(0);
                        String descripSubheading = descripcion + " " + registros.getString(4);
                        subheadingActual.setTariffSubheadingDescription(descripSubheading);
                    }

                } else {
                    subheadingActual.setTariffSubheadingDescription(registros.getString(4));
                }

                subheadingActual.setIdTariffSubheading(registros.getInt(0));
                subheadingActual.setIdTariffPreSubheading(registros.getInt(1));
                subheadingActual.setIdTariffHeading(registros.getInt(2));
                subheadingActual.setTariffSubheadingCode(registros.getString(3));
                subheadingActual.setTariffSubheadingIcon(registros.getInt(5));
                subheadingses.add(subheadingActual);
            }

        }

        return subheadingses;
    }

    public ArrayList<Fractions> getFractionsBD(int idTariffSubheading) {
        ArrayList<Fractions> fractions = new ArrayList<>();

        String query =
                "SELECT * "
                        + " FROM "
                        + ConstantesBaseDatos.TABLE_TARIFF_FRACTIONS + " FR "
                        + "  WHERE FR."
                        + ConstantesBaseDatos.TABLE_TARIFF_FRACTIONS_SUBHEADINGS_ID + " = " + idTariffSubheading;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {

            Fractions fractionActual = new Fractions();

            fractionActual.setIdTariffFraction(registros.getInt(0));
            fractionActual.setIdTariffChapter(registros.getInt(1));
            fractionActual.setIdTariffHeading(registros.getInt(2));
            fractionActual.setIdTariffSubheading(registros.getInt(3));
            fractionActual.setTariffFractionCode(registros.getString(4));
            fractionActual.setTariffFractionDescription(registros.getString(5));
            fractionActual.setTariffFractionIcon(registros.getInt(7));

            fractions.add(fractionActual);

        }

        return fractions;
    }


    public ArrayList<Fractions> getFractionsInf(String text) {
        ArrayList<Fractions> fractions = new ArrayList<>();

        int id          = 0;
        String query    =
                "SELECT * "
                        + " FROM "
                        + ConstantesBaseDatos.TABLE_TARIFF_FRACTIONS + " FR "
                        + "  WHERE FR."
                        + ConstantesBaseDatos.TABLE_TARIFF_FRACTIONS_DESCRIP + " LIKE '%" + text + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {

            Fractions fractionActual = new Fractions();
            id = registros.getInt(0);

            fractionActual.setIdTariffFraction(id);
            fractionActual.setIdTariffChapter(registros.getInt(1));
            fractionActual.setIdTariffHeading(registros.getInt(2));
            fractionActual.setIdTariffSubheading(registros.getInt(3));
            fractionActual.setTariffFractionCode(registros.getString(4));
            fractionActual.setTariffFractionDescription(registros.getString(5));
            fractionActual.setTariffFractionIcon(registros.getInt(7));

            fractions.add(fractionActual);

        }

        return fractions;
    }

    public Boolean compareUser (String alias, String pass) {
        Boolean response = false;


        String query = "SELECT status_usuario FROM user_data WHERE email_usuario = '" + alias+ "' AND password_usuario = " + "'" + pass +"'";
        SQLiteDatabase db8 = this.getWritableDatabase();
        Cursor registros = db8.rawQuery(query, null);
        int id = 0;


        while (registros.moveToNext()) {
            id =1;
        }

        if (id == 0) {
            response = false;
        } else {
            response = true;
        }

        return response;
    }

    public void insertAllChapters(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_TARIFF_CHAPTERS, null, contentValues);
        db.close();
    }

    public void insertHeadings(ContentValues contentValues2) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_TARIFF_HEADINGS, null, contentValues2);
        db.close();
    }

    public Chapters getChaptersInf(String CodeChapter) {

        Chapters chapters = new Chapters();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_TARIFF_CHAPTERS +
                " WHERE " + ConstantesBaseDatos.TABLE_TARIFF_CHAPTERS_CODE + " = '" + CodeChapter + "'";

        SQLiteDatabase db8 = this.getWritableDatabase();
        Cursor registros = db8.rawQuery(query, null);

        int id = 0;
        String codigo = "";
        String descripcion = "";
        int icono = R.drawable.cap_02;

        int counter = 1;
        while (registros.moveToNext()) {
            if (counter == 1) {
                id = registros.getInt(0);
                codigo = registros.getString(1);
                descripcion = registros.getString(2);
                icono = registros.getInt(3);

                chapters.setId(registros.getInt(0));
                chapters.setCodigo(registros.getString(1));
                chapters.setDescripcion(registros.getString(2));
                chapters.setIcono(registros.getInt(3));
            }

            counter++;
        }
        db8.close();

        return (new Chapters(id, codigo, descripcion, icono));

    }


    public Headings getHeadingsInf(String headingCode) {
        Headings headings = new Headings();

        int idTariffHeading = 0;
        int idTariffChapter = 0;
        String tariffHeadingCode = "0000";
        String tariffHeadingDescription = "Sin valor";
        int tariffHeadingIcon = R.drawable.cap_01;

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_TARIFF_HEADINGS +
                " WHERE " + ConstantesBaseDatos.TABLE_TARIFF_HEADINGS_CODE + " = '" + headingCode + "'";

        SQLiteDatabase db8 = this.getWritableDatabase();
        Cursor registros = db8.rawQuery(query, null);

        while (registros.moveToNext()) {
            idTariffHeading = registros.getInt(0);
            idTariffChapter = registros.getInt(1);
            tariffHeadingCode = registros.getString(2);
            tariffHeadingDescription = registros.getString(3);

            headings.setIdTariffHeading(registros.getInt(0));
            headings.setIdTariffChapter(registros.getInt(1));
            headings.setTariffHeadingCode(registros.getString(2));
            headings.setTariffHeadingDescription(registros.getString(3));
            headings.setTariffHeadingIcon(registros.getInt(4));
        }

        return (new Headings(idTariffHeading, idTariffChapter, tariffHeadingCode, tariffHeadingDescription, tariffHeadingIcon));
    }

    public Subheadings getSubheadingsInf(String subheadingCode) {
        Subheadings subheadings = new Subheadings();

        String descripSubheading = "";
        int idTariffSubheading = 0;
        int idTariffPreSubheading = 0;
        int idTariffHeading = 0;
        String tariffSubheadingCode = "Sin valor";
        String tariffSubheadingDescription = "Sin valor";
        int tariffSubheadingIcon = R.drawable.cap_01;

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_TARIFF_SUBHEADINGS +
                " WHERE " + ConstantesBaseDatos.TABLE_TARIFF_SUBHEADINGS_CODE + " = '" + subheadingCode + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);


        while (registros.moveToNext()) {

            if (registros.getString(4) != null) {
                if (registros.getInt(1) != 1) {

                    String queryDescr = "SELECT " + ConstantesBaseDatos.TABLE_TARIFF_PRESUBHEADINGS_DESCRIP + " FROM " + ConstantesBaseDatos.TABLE_TARIFF_PRESUBHEADINGS +
                            " WHERE " + ConstantesBaseDatos.TABLE_TARIFF_PRESUBHEADINGS_PRESUBHEADINGS_ID + " = " + registros.getInt(1);
                    Cursor registros2 = db.rawQuery(queryDescr, null);

                    while (registros2.moveToNext()) {
                        String descripcion = registros2.getString(0);
                        descripSubheading = descripcion + " " + registros.getString(4);
                        subheadings.setTariffSubheadingDescription(descripSubheading);
                    }

                } else {
                    descripSubheading = registros.getString(4);
                    subheadings.setTariffSubheadingDescription(descripSubheading);
                }

                idTariffSubheading = registros.getInt(0);
                idTariffPreSubheading = registros.getInt(1);
                idTariffHeading = registros.getInt(2);
                tariffSubheadingCode = registros.getString(3);
                tariffSubheadingDescription = descripSubheading;

                subheadings.setIdTariffSubheading(registros.getInt(0));
                subheadings.setIdTariffPreSubheading(registros.getInt(1));
                subheadings.setIdTariffHeading(registros.getInt(2));
                subheadings.setTariffSubheadingCode(registros.getString(3));
                subheadings.setTariffSubheadingIcon(tariffSubheadingIcon);

            }

        }

        return (new Subheadings(idTariffSubheading, idTariffPreSubheading, idTariffHeading, tariffSubheadingCode, tariffSubheadingDescription, tariffSubheadingIcon));
    }



    public Boolean insertFavouites(String fraction) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fraccion", fraction);
        db.insert("Favoritos", null, contentValues);
        return true;
    }

    public Boolean insertUserData(String email, String password, int status) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email_usuario",  email.toString());
        contentValues.put("password_usuario",  password.toString());
        contentValues.put("status_usuario",  1);
        db.insert("user_data", null, contentValues);
        db.close();
        return true;
    }


    public ArrayList<Fractions> getFavourites () {
        ArrayList<Fractions> fractions = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_FAVORITOS ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {

            String queryFrac = "SELECT * "
                            + " FROM "
                            + ConstantesBaseDatos.TABLE_TARIFF_FRACTIONS + " FR "
                            + "  WHERE FR."
                            + ConstantesBaseDatos.TABLE_TARIFF_FRACTIONS_CODE + " = '" + registros.getString(1) + "'" ;

            Cursor registros2 = db.rawQuery(queryFrac, null);

            while (registros2.moveToNext()) {
                int id = registros2.getInt(0);
                fractions.add(new Fractions(registros2.getInt(0), registros2.getInt(1), registros2.getInt(2), registros2.getInt(3), registros2.getString(4), registros2.getString(5), registros2.getInt(7)));
            }

        }

        return fractions;
    }

    public ArrayList<Object> getUserData (){
        ArrayList<Object> userData = new ArrayList <Object>();
        String query = "SELECT email_usuario, password_usuario, status_usuario FROM user_data WHERE status_usuario = 1";

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor data = DB.rawQuery(query, null);

        while (data.moveToNext()) {
            userData.add(data.getString(0));
            userData.add(data.getString(1));
            userData.add(data.getInt(2));
        }
        return userData;
    }

}

