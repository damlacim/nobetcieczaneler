package com.damlacim.nobetcieczaneler.model;

public class PharmacyRequest {

    int provinceCode;
    String districtsName;
    final int watchDay = 0;

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getDistrictsName() {
        return districtsName;
    }

    public void setDistrictsName(String districtsName) {
        this.districtsName = districtsName;
    }
}
