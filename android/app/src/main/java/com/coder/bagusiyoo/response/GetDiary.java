package com.coder.bagusiyoo.response;

import com.coder.bagusiyoo.model.DiaryModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDiary {
    @SerializedName("result")
    List<DiaryModel> listDiary;

    @SerializedName("message")
    final private String message;

    @SerializedName("status")
    final private String status;

    public GetDiary(List<DiaryModel> listDiary, String message, String status) {
        this.listDiary = listDiary;
        this.message = message;
        this.status = status;
    }

    public List<DiaryModel> getListDiary() {
        return listDiary;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
