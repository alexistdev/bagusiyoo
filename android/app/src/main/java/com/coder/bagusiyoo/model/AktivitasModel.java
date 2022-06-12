package com.coder.bagusiyoo.model;

import com.google.gson.annotations.SerializedName;

public class AktivitasModel {
    @SerializedName("name")
    private final String namaAktivitas;

    public AktivitasModel(String namaAktivitas) {
        this.namaAktivitas = namaAktivitas;
    }

    public String getNamaAktivitas() {
        return namaAktivitas;
    }
}
