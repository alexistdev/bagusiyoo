package com.coder.bagusiyoo.response;

import com.coder.bagusiyoo.model.AktivitasModel;
import com.coder.bagusiyoo.model.DiaryModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAktivitas {
    @SerializedName("result")
    List<AktivitasModel> listAktivitas;

    @SerializedName("message")
    final private String message;

    @SerializedName("status")
    final private String status;

    public GetAktivitas(List<AktivitasModel> listAktivitas, String message, String status) {
        this.listAktivitas = listAktivitas;
        this.message = message;
        this.status = status;
    }

    public List<AktivitasModel> getListAktivitas() {
        return listAktivitas;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
