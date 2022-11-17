package com.coder.bagusiyoo.response;

import com.coder.bagusiyoo.model.LabModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLab {
    @SerializedName("status")
    private final Boolean status;

    @SerializedName("message")
    private final String message;

    @SerializedName("result")
    private final List<LabModel> daftarLab;

    public GetLab(Boolean status, String message, List<LabModel> daftarLab) {
        this.status = status;
        this.message = message;
        this.daftarLab = daftarLab;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<LabModel> getDaftarLab() {
        return daftarLab;
    }
}
