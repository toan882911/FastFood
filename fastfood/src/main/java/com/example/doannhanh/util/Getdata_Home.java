package com.example.doannhanh.util;

import com.example.doannhanh.Retrofit2.APIutils;
import com.example.doannhanh.Retrofit2.DataClient;
import com.example.doannhanh.model.DoAn;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Getdata_Home {
    List<DoAn> mangdoan;
    public List<DoAn> getdata(){
        DataClient dataClient = APIutils.getData();
        Call<List<DoAn>> callback = dataClient.GetData();
        callback.enqueue(new Callback<List<DoAn>>() {
            @Override
            public void onResponse(Call<List<DoAn>> call, Response<List<DoAn>> response) {
                mangdoan = (List<DoAn>) response.body();
            }
            @Override
            public void onFailure(Call<List<DoAn>> call, Throwable t) {

            }
        });
        return mangdoan;
    }
}
