package com.example.sistemascasa.tigie.db;

public class ConstantesBaseDatos {

    public static final String DATABASE_NAME= "TigieDB3";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_TARIFF_CHAPTERS            = "tariffChapters";
    public static final String TABLE_TARIFF_CHAPTERS_ID         = "id";
    public static final String TABLE_TARIFF_CHAPTERS_CODE       = "tariffChapterCode";
    public static final String TABLE_TARIFF_CHAPTERS_DESCRIP    = "tariffChapterDescription";
    public static final String TABLE_TARIFF_CHAPTERS_ICONO      = "tariffChapterIcon";


    public static final String TABLE_TARIFF_HEADINGS            = "tariffHeadings";
    public static final String TABLE_TARIFF_HEADINGS_HEADINGS_ID= "idTariffHeading";
    public static final String TABLE_TARIFF_HEADINGS_CHAPTERS_ID= "idTariffChapter";
    public static final String TABLE_TARIFF_HEADINGS_CODE       = "tariffHeadingCode";
    public static final String TABLE_TARIFF_HEADINGS_DESCRIP    = "tariffHeadingDescription";
    public static final String TABLE_TARIFF_HEADINGS_ICONO      = "tariffHeadingIcon";


    public static final String TABLE_TARIFF_SUBHEADINGS             = "tariffSubheadings";
    public static final String TABLE_TARIFF_SUBHEADINGS_PRESUBHEADING_ID = "idTariffPreSubheading";
    public static final String TABLE_TARIFF_SUBHEADINGS_ID          = "idTariffSubheading";
    public static final String TABLE_TARIFF_SUBHEADINGS_HEADING_ID  = "idTariffHeading";
    public static final String TABLE_TARIFF_SUBHEADINGS_CODE        = "tariffSubheadingCode";
    public static final String TABLE_TARIFF_SUBHEADINGS_DESCRIP     = "tariffSubheadingDescription";
    public static final String TABLE_TARIFF_SUBHEADINGS_ICONO       = "tariffSubheadingIcon";

    public static final String TABLE_TARIFF_PRESUBHEADINGS          = "tariffPreSubheadings";
    public static final String TABLE_TARIFF_PRESUBHEADINGS_PRESUBHEADINGS_ID = "idTariffPreSubheading";
    public static final String TABLE_TARIFF_PRESUBHEADINGS_CODE        = "tariffPreSubheadingCode";
    public static final String TABLE_TARIFF_PRESUBHEADINGS_DESCRIP     = "tariffPreSubheadingDescription";
    public static final String TABLE_TARIFF_PRESUBHEADINGS_ICONO       = "tariffPreSubheadingIcon";

    public static final String TABLE_TARIFF_FRACTIONS                = "tariffFractions";
    public static final String TABLE_TARIFF_FRACTIONS_FRACTIONS_ID   = "idTariffFraction";
    public static final String TABLE_TARIFF_FRACTIONS_CHAPTERS_ID    = "idTariffChapter";
    public static final String TABLE_TARIFF_FRACTIONS_HEADINGS_ID    = "idTariffHeading";
    public static final String TABLE_TARIFF_FRACTIONS_SUBHEADINGS_ID = "idTariffSubheading";
    public static final String TABLE_TARIFF_FRACTIONS_CODE           = "tariffFractionCode";
    public static final String TABLE_TARIFF_FRACTIONS_DESCRIP        = "tariffFractionDescription";
    public static final String TABLE_TARIFF_FRACTIONS_WORDS          = "tariffFractionWords";
    public static final String TABLE_TARIFF_FRACTIONS_ICONO          = "tariffSubheadingIcon";

    public static final String TABLE_FAVORITOS = "Favoritos";
    public static final String TABLE_FAVORITOS_ID = "favouritesId";
    public static final String TABLE_FAVORITOS_FRACTION  = "favouritesFraccion";


}
