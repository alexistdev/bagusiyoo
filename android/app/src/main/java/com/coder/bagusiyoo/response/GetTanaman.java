package com.coder.bagusiyoo.response;

import com.coder.bagusiyoo.model.TanamanModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTanaman {
    @SerializedName("result")
    final private List<TanamanModel> itemTanaman;

    public GetTanaman(List<TanamanModel> itemTanaman) {
        this.itemTanaman = itemTanaman;
    }

    public List<TanamanModel> getItemTanaman() {
        return itemTanaman;
    }
}
