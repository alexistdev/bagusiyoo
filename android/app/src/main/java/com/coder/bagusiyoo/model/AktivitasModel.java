package com.coder.bagusiyoo.model;

import com.google.gson.annotations.SerializedName;

public class AktivitasModel {
    @SerializedName("id")
    private final String idAktvitas;

    @SerializedName("name")
    private final String namaAktivitas;


    public AktivitasModel(String idAktvitas, String namaAktivitas) {
        this.idAktvitas = idAktvitas;
        this.namaAktivitas = namaAktivitas;
    }

    public String getIdAktvitas() {
        return idAktvitas;
    }

    public String getNamaAktivitas() {
        return namaAktivitas;
    }
}
