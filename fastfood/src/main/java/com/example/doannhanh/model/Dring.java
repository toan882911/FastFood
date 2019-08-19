package com.example.doannhanh.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dring {

@SerializedName("id")
@Expose
private String id;
@SerializedName("tenloai")
@Expose
private String tenloai;
@SerializedName("tenmonan")
@Expose
private String tenmonan;
@SerializedName("noidung")
@Expose
private String noidung;
@SerializedName("anh")
@Expose
private String anh;
@SerializedName("giatien")
@Expose
private String giatien;
@SerializedName("giamgia")
@Expose
private String giamgia;
@SerializedName("yeuthich")
@Expose
private String yeuthich;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getTenloai() {
return tenloai;
}

public void setTenloai(String tenloai) {
this.tenloai = tenloai;
}

public String getTenmonan() {
return tenmonan;
}

public void setTenmonan(String tenmonan) {
this.tenmonan = tenmonan;
}

public String getNoidung() {
return noidung;
}

public void setNoidung(String noidung) {
this.noidung = noidung;
}

public String getAnh() {
return anh;
}

public void setAnh(String anh) {
this.anh = anh;
}

public String getGiatien() {
return giatien;
}

public void setGiatien(String giatien) {
this.giatien = giatien;
}

public String getGiamgia() {
return giamgia;
}

public void setGiamgia(String giamgia) {
this.giamgia = giamgia;
}

public String getYeuthich() {
return yeuthich;
}

public void setYeuthich(String yeuthich) {
this.yeuthich = yeuthich;
}

}