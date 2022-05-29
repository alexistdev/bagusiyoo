package com.coder.bagusiyoo.model;

import com.google.gson.annotations.SerializedName;

public class DiaryModel {
   @SerializedName("id")
   private final String idDiary;

   @SerializedName("tanaman_id")
   private final String idTanaman;

   @SerializedName("name")
   private final String namaTanaman;

   @SerializedName("harike")
   private final String harike;

   @SerializedName("created_at")
   private final String mulaiTanam;

   @SerializedName("panen")
   private final String panen;

   public DiaryModel(String idDiary, String idTanaman, String namaTanaman, String harike, String mulaiTanam, String panen) {
      this.idDiary = idDiary;
      this.idTanaman = idTanaman;
      this.namaTanaman = namaTanaman;
      this.harike = harike;
      this.mulaiTanam = mulaiTanam;
      this.panen = panen;
   }

   public String getIdDiary() {
      return idDiary;
   }

   public String getIdTanaman() {
      return idTanaman;
   }

   public String getNamaTanaman() {
      return namaTanaman;
   }

   public String getHarike() {
      return harike;
   }

   public String getMulaiTanam() {
      return mulaiTanam;
   }

   public String getPanen() {
      return panen;
   }
}
