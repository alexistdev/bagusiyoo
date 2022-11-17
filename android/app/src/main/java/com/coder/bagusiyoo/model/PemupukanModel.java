package com.coder.bagusiyoo.model;

import com.google.gson.annotations.SerializedName;

public class PemupukanModel {
    @SerializedName("nozzel")
    private final String nozzel;
    @SerializedName("id_lab")
    private final String id_lab;

    public PemupukanModel(String nozzel, String id_lab) {
        this.nozzel = nozzel;
        this.id_lab = id_lab;
    }

    public String getNozzel() {
        return nozzel;
    }

    public String getId_lab() {
        return id_lab;
    }
}
