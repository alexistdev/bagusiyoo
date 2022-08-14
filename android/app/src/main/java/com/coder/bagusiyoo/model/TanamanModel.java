package com.coder.bagusiyoo.model;

import com.google.gson.annotations.SerializedName;

public class TanamanModel {
    @SerializedName("id")
    final private String idTanaman;

    @SerializedName("name")
    final private String namaTanaman;

    public TanamanModel(String idTanaman, String namaTanaman) {
        this.idTanaman = idTanaman;
        this.namaTanaman = namaTanaman;
    }

    public String getIdTanaman() {
        return idTanaman;
    }

    public String getNamaTanaman() {
        return namaTanaman;
    }

    @Override
    public String toString() {
        return namaTanaman;
    }
}
