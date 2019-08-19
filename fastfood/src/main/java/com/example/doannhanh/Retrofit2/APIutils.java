package com.example.doannhanh.Retrofit2;

public class APIutils {
    public static final String Base_url="http://abc12221.000webhostapp.com/";
    public static DataClient getData(){
        return RetrofitClient.getClient(Base_url).create(DataClient.class);
    }
}
