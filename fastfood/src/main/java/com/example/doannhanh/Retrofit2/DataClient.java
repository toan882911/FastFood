package com.example.doannhanh.Retrofit2;

import com.example.doannhanh.model.Cart;
import com.example.doannhanh.model.DoAn;
import com.example.doannhanh.model.Dring;
import com.example.doannhanh.model.Food;
import com.example.doannhanh.model.Search;
import com.example.doannhanh.model.Table;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataClient {
//    @Multipart
//    @POST("upload.php")
//    retrofit2.Call<String> UploadPhoto(@Part MultipartBody.Part photo);
//
    @FormUrlEncoded
    @POST("order.php")
    retrofit2.Call<String> Order(@Field("idfaceboook") String idfaceboook,
                                 @Field("tenmon") String tenmon,
                                 @Field("anh") String anh,
                                 @Field("giatien") String giatien,
                                 @Field("tongtien") String tongtien,
                                 @Field("hoten") String hoten,
                                 @Field("diachi") String diachi,
                                 @Field("sdt") String sdt,
                                 @Field("ghichu") String ghichu);
    @FormUrlEncoded
    @POST("settable.php")
    retrofit2.Call<String> SetTable(@Field("hoten") String hoten,
                                    @Field("idfacebook") String idfacebook,
                                    @Field("banso") Integer banso,
                                    @Field("songuoi") Integer songuoi,
                                    @Field("sdt") String sdt,
                                    @Field("thoigian") String thoigian);

    @FormUrlEncoded
    @POST("search.php")
    retrofit2.Call<List<DoAn>> Search(@Field("tenmonan") String tenmonan);

    @FormUrlEncoded
    @POST("updatetable.php")
    retrofit2.Call<String> UpdateTable(@Field("soban") Integer soban);

    @FormUrlEncoded
    @POST("checktable.php")
    retrofit2.Call<Integer> CheckTable(@Field("soban") Integer soban);

    @FormUrlEncoded
    @POST("like.php")
    retrofit2.Call<String> Like(@Field("like") String like, @Field("idmonan") Integer id, @Field("idfacebook") String idfacebook);

    @GET("getlike.php")
    retrofit2.Call<String> GetLike(@Query("idmonan") Integer idmonan);

    @GET("checklike.php")
    retrofit2.Call<String> CheckLike(@Query("idmonan") Integer idmonan, @Query("idfacebook") String idfacebook);

    @GET("getdata.php")
    retrofit2.Call<List<DoAn>> GetData();
    @GET("getdoan.php")
    retrofit2.Call<List<Food>> GetDoAn();
    @GET("getdouong.php")
    retrofit2.Call<List<Dring>> GetDoUong();
    @GET("getcart.php")
    retrofit2.Call<List<Cart>> GetCart(@Query("idface") String idface);
    @GET("gettable.php")
    retrofit2.Call<List<Table>> GetTable(@Query("idface") String idface);
//    @GET("delete.php")
//    retrofit2.Call<String> DeleteData(@Query("id") String id,
//                                      @Query("hinhanh") String hinhanh);
    @FormUrlEncoded
    @POST("search.php")
    retrofit2.Call<List<Food>> Search2(@Field("tenmonan") String tenmonan);
    @FormUrlEncoded
    @POST("search.php")
    retrofit2.Call<List<Dring>> Search3(@Field("tenmonan") String tenmonan);
    @FormUrlEncoded
    @POST("delete-food.php")
    retrofit2.Call<String> DeleteFood(@Field("id") String id);
    @FormUrlEncoded
    @POST("delete-table.php")
    retrofit2.Call<String> DeleteTable(@Field("id") String id,@Field("soban") String soban);
    @FormUrlEncoded
    @POST("update-food.php")
    retrofit2.Call<String> UpdateFood(@Field("id") String id,
                                      @Field("soluong") Integer soluong,
                                      @Field("tongtien") Integer tongtien);
}
