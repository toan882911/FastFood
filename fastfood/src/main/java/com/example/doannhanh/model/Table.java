package com.example.doannhanh.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

@SerializedName("id")
@Expose
private String id;
@SerializedName("idfacebook")
@Expose
private String idfacebook;
@SerializedName("hoten")
@Expose
private String hoten;
@SerializedName("soban")
@Expose
private String soban;
@SerializedName("songuoi")
@Expose
private String songuoi;
@SerializedName("sdt")
@Expose
private String sdt;
@SerializedName("thoigian")
@Expose
private String thoigian;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getIdfacebook() {
return idfacebook;
}

public void setIdfacebook(String idfacebook) {
this.idfacebook = idfacebook;
}

public String getHoten() {
return hoten;
}

public void setHoten(String hoten) {
this.hoten = hoten;
}

public String getSoban() {
return soban;
}

public void setSoban(String soban) {
this.soban = soban;
}

public String getSonguoi() {
return songuoi;
}

public void setSonguoi(String songuoi) {
this.songuoi = songuoi;
}

public String getSdt() {
return sdt;
}

public void setSdt(String sdt) {
this.sdt = sdt;
}

public String getThoigian() {
return thoigian;
}

public void setThoigian(String thoigian) {
this.thoigian = thoigian;
}

}