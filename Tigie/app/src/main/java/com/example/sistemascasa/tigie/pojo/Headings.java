package com.example.sistemascasa.tigie.pojo;

public class Headings {

    private int idTariffHeading;
    private int idTariffChapter;
    private String tariffHeadingCode;
    private String tariffHeadingDescription;
    private int tariffHeadingIcon;
    private int idChapterTmp;

    public Headings () {

    }

    public Headings (int idTariffHeading, int idTariffChapter, String tariffHeadingCode, String tariffHeadingDescription, int tariffHeadingIcon) {
        this.idTariffHeading            = idTariffHeading;
        this.idTariffChapter            = idTariffChapter;
        this.tariffHeadingCode          = tariffHeadingCode;
        this.tariffHeadingDescription   = tariffHeadingDescription;
        this.tariffHeadingIcon          = tariffHeadingIcon;
    }

    public int getIdTariffHeading() {
        return idTariffHeading;
    }

    public void setIdTariffHeading(int idTariffHeading) {
        this.idTariffHeading = idTariffHeading;
    }

    public int getIdTariffChapter() {
        return idTariffChapter;
    }

    public void setIdTariffChapter(int idTariffChapter) {
        this.idTariffChapter = idTariffChapter;
    }

    public String getTariffHeadingCode() {
        return tariffHeadingCode;
    }

    public void setTariffHeadingCode(String tariffHeadingCode) {
        this.tariffHeadingCode = tariffHeadingCode;
    }

    public String getTariffHeadingDescription() {
        return tariffHeadingDescription;
    }

    public void setTariffHeadingDescription(String tariffHeadingDescription) {
        this.tariffHeadingDescription = tariffHeadingDescription;
    }

    public int getTariffHeadingIcon() {
        return tariffHeadingIcon;
    }

    public void setTariffHeadingIcon(int tariffHeadingIcon) {
        this.tariffHeadingIcon = tariffHeadingIcon;
    }

    public int getIdChapterTmp() {
        return idChapterTmp;
    }

    public void setIdChapterTmp(int idChapterTmp) {
        this.idChapterTmp = idChapterTmp;
    }

}
