package com.coder.bagusiyoo.model;

import com.google.gson.annotations.SerializedName;

public class LabModel {
    @SerializedName("id")
    private final String idLab;

    @SerializedName("name")
    private final String namaLab;

    public LabModel(String idLab, String namaLab) {
        this.idLab = idLab;
        this.namaLab = namaLab;
    }

    public String getIdLab() {
        return idLab;
    }

    public String getNamaLab() {
        return namaLab;
    }
}
