package com.coder.bagusiyoo.model;

import com.google.gson.annotations.SerializedName;

public class SiramModel {
    @SerializedName("pump")
    private final String pump;
    @SerializedName("id_lab")
    private final String id_lab;

    public SiramModel(String pump, String id_lab) {
        this.pump = pump;
        this.id_lab = id_lab;
    }

    public String getPump() {
        return pump;
    }

    public String getId_lab() {
        return id_lab;
    }
}
