package com.coder.bagusiyoo.model;

import com.google.gson.annotations.SerializedName;

public class SensorModel {
    @SerializedName("status")
    private final Boolean status;

    @SerializedName("message")
    private final String message;

    @SerializedName("suhu_udara")
    private final String suhuUdara;

    @SerializedName("kelembaban_udara")
    private final String KelembabanUdara;

    @SerializedName("ph_tanah")
    private final String phTanah;

    @SerializedName("kelembaban_tanah")
    private final String kelembabanTanah;

    @SerializedName("pump")
    private final String statusPump;

    @SerializedName("nozzel")
    private final String statusNozzel;

    @SerializedName("update")
    private final String updateDate;

    public SensorModel(Boolean status, String message, String suhuUdara, String kelembabanUdara, String phTanah, String kelembabanTanah, String statusPump, String statusNozzel, String updateDate) {
        this.status = status;
        this.message = message;
        this.suhuUdara = suhuUdara;
        KelembabanUdara = kelembabanUdara;
        this.phTanah = phTanah;
        this.kelembabanTanah = kelembabanTanah;
        this.statusPump = statusPump;
        this.statusNozzel = statusNozzel;
        this.updateDate = updateDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getSuhuUdara() {
        return suhuUdara;
    }

    public String getKelembabanUdara() {
        return KelembabanUdara;
    }

    public String getPhTanah() {
        return phTanah;
    }

    public String getKelembabanTanah() {
        return kelembabanTanah;
    }

    public String getStatusPump() {
        return statusPump;
    }

    public String getStatusNozzel() {
        return statusNozzel;
    }

    public String getUpdateDate() {
        return updateDate;
    }
}
