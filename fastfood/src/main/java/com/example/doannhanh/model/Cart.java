package com.example.doannhanh.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart {

@SerializedName("id")
@Expose
private String id;
@SerializedName("tenmon")
@Expose
private String tenmon;
@SerializedName("anh")
@Expose
private String anh;
@SerializedName("soluong")
@Expose
private String soluong;
@SerializedName("giatien")
@Expose
private String giatien;
@SerializedName("tongtien")
@Expose
private String tongtien;
@SerializedName("hoten")
@Expose
private String hoten;
@SerializedName("idfacebook")
@Expose
private String idfacebook;
@SerializedName("diachi")
@Expose
private String diachi;
@SerializedName("sdt")
@Expose
private String sdt;
@SerializedName("ghichu")
@Expose
private String ghichu;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getTenmon() {
return tenmon;
}

public void setTenmon(String tenmon) {
this.tenmon = tenmon;
}

public String getAnh() {
return anh;
}

public void setAnh(String anh) {
this.anh = anh;
}

public String getSoluong() {
return soluong;
}

public void setSoluong(String soluong) {
this.soluong = soluong;
}

public String getGiatien() {
return giatien;
}

public void setGiatien(String giatien) {
this.giatien = giatien;
}

public String getTongtien() {
return tongtien;
}

public void setTongtien(String tongtien) {
this.tongtien = tongtien;
}

public String getHoten() {
return hoten;
}

public void setHoten(String hoten) {
this.hoten = hoten;
}

public String getIdfacebook() {
return idfacebook;
}

public void setIdfacebook(String idfacebook) {
this.idfacebook = idfacebook;
}

public String getDiachi() {
return diachi;
}

public void setDiachi(String diachi) {
this.diachi = diachi;
}

public String getSdt() {
return sdt;
}

public void setSdt(String sdt) {
this.sdt = sdt;
}

public String getGhichu() {
return ghichu;
}

public void setGhichu(String ghichu) {
this.ghichu = ghichu;
}

}