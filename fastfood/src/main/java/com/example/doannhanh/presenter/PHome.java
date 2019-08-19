package com.example.doannhanh.presenter;

import android.content.Context;
import android.util.Log;

import com.example.doannhanh.Retrofit2.APIutils;
import com.example.doannhanh.Retrofit2.DataClient;
import com.example.doannhanh.model.DoAn;
import com.example.doannhanh.util.Getdata_Home;
import com.example.doannhanh.view.Adapter_home;
import com.example.doannhanh.view.IHome;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PHome implements IPHome {
    IHome view;
    Context context;
    Getdata_Home getdata;
    Adapter_home adapter;
    List<DoAn> data = new ArrayList<>();
    public PHome(IHome view , Context context ) {
        this.view = view;
        this.context = context;
    }
    @Override
    public void LoadData() {
        view.loadding();
        DataClient dataClient = APIutils.getData();
        Call<List<DoAn>> callback = dataClient.GetData();
        callback.enqueue(new Callback<List<DoAn>>() {
            @Override
            public void onResponse(Call<List<DoAn>> call, Response<List<DoAn>> response) {
                data = (List<DoAn>) response.body();
                adapter = new Adapter_home(context,data);
                if(data.size()>0){
                    view.LoadData(data,adapter);
                }else{
                    view.LoadError("Load Lỗi",context);
                }
                view.loadcomplete();
            }
            @Override
            public void onFailure(Call<List<DoAn>> call, Throwable t) {
                view.loadcomplete();
            }
        });


    }

    @Override
    public void Search(String tenmonan) {
            view.loadding();
            DataClient dataClient = APIutils.getData();
            Call<List<DoAn>> callback = dataClient.Search(tenmonan);
            callback.enqueue(new Callback<List<DoAn>>() {
                @Override
                public void onResponse(Call<List<DoAn>> call, Response<List<DoAn>> response) {
                    data = (List<DoAn>) response.body();
                    adapter = new Adapter_home(context,data);
                    if(data.size()>0){
                        view.LoadData(data,adapter);
                    }else{
                        view.LoadError("không tìm thấy",context);
                    }
                    view.loadcomplete();
                }
                @Override
                public void onFailure(Call<List<DoAn>> call, Throwable t) {
                    Log.d("bbb", "onFailure: ");
                    view.loadcomplete();
                }
            });

    }

}
