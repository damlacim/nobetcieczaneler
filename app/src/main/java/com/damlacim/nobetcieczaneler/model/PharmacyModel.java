package com.damlacim.nobetcieczaneler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PharmacyModel {

    @SerializedName("eczaneAdi")
    @Expose
    private String eczaneAdi;

    @SerializedName("eczaneIlAdi")
    @Expose
    private String eczaneIlAdi;

    @SerializedName("eczaneIlceAdi")
    @Expose
    private String eczaneIlceAdi;

    @SerializedName("eczaneAdres")
    @Expose
    private String eczaneAdres;

    @SerializedName("tarih")
    @Expose
    private String tarih;

    @SerializedName("baslangic")
    @Expose
    private String baslangic;

    @SerializedName("bitis")
    @Expose
    private String bitis;

    @SerializedName("enlem")
    @Expose
    private String enlem;

    @SerializedName("boylam")
    @Expose
    private String boylam;

    @SerializedName("glnNo")
    @Expose
    private String glnNo;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("olusturmaTarihi")
    @Expose
    private String olusturmaTarihi;

    @SerializedName("guncellemeTarihi")
    @Expose
    private Object guncellemeTarihi;

    @SerializedName("silindi")
    @Expose
    private Object silindi;

    public String getEczaneAdi() {
        return eczaneAdi;
    }

    public String getEczaneAdres() {
        return eczaneAdres;
    }

    public String getEnlem() {
        return enlem;
    }

    public String getBoylam() {
        return boylam;
    }

}