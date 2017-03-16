package com.example.sistemascasa.tigie.pojo;

public class Subheadings {

    private int idTariffSubheading;
    private int idTariffPreSubheading;
    private int idTariffHeading;
    private String tariffSubheadingCode;
    private String tariffSubheadingDescription;
    private int tariffSubheadingIcon;


    public  Subheadings () {

    }

    public  Subheadings (int idTariffSubheading, int idTariffPreSubheading, int idTariffHeading, String tariffSubheadingCode, String tariffSubheadingDescription, int tariffSubheadingIcon) {
        this.idTariffSubheading     = idTariffSubheading;
        this.idTariffPreSubheading  = idTariffPreSubheading;
        this.idTariffHeading        = idTariffHeading;
        this.tariffSubheadingCode   = tariffSubheadingCode;
        this.tariffSubheadingDescription = tariffSubheadingDescription;
        this.tariffSubheadingIcon   = tariffSubheadingIcon;
    }


    public int getTariffSubheadingIcon() {
        return tariffSubheadingIcon;
    }

    public void setTariffSubheadingIcon(int tariffSubheadingIcon) {
        this.tariffSubheadingIcon = tariffSubheadingIcon;
    }

    public String getTariffSubheadingDescription() {
        return tariffSubheadingDescription;
    }

    public void setTariffSubheadingDescription(String tariffSubheadingDescription) {
        this.tariffSubheadingDescription = tariffSubheadingDescription;
    }

    public String getTariffSubheadingCode() {
        return tariffSubheadingCode;
    }

    public void setTariffSubheadingCode(String tariffSubheadingCode) {
        this.tariffSubheadingCode = tariffSubheadingCode;
    }

    public int getIdTariffHeading() {
        return idTariffHeading;
    }

    public void setIdTariffHeading(int idTariffHeading) {
        this.idTariffHeading = idTariffHeading;
    }

    public int getIdTariffPreSubheading() {
        return idTariffPreSubheading;
    }

    public void setIdTariffPreSubheading(int idTariffPreSubheading) {
        this.idTariffPreSubheading = idTariffPreSubheading;
    }

    public int getIdTariffSubheading() {
        return idTariffSubheading;
    }

    public void setIdTariffSubheading(int idTariffSubheading) {
        this.idTariffSubheading = idTariffSubheading;
    }


}


