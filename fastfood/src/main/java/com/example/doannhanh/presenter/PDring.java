package com.example.doannhanh.presenter;

import android.content.Context;
import android.util.Log;

import com.example.doannhanh.Retrofit2.APIutils;
import com.example.doannhanh.Retrofit2.DataClient;
import com.example.doannhanh.model.DoAn;
import com.example.doannhanh.model.Dring;
import com.example.doannhanh.model.Food;
import com.example.doannhanh.view.Adapter_dring;
import com.example.doannhanh.view.Adapter_food;
import com.example.doannhanh.view.Adapter_home;
import com.example.doannhanh.view.IDring;
import com.example.doannhanh.view.IFood;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PDring implements IPDring {
    Context context;
    IDring view;
    Adapter_dring adapter;
    List<Dring> data = new ArrayList<>();
    public PDring(IDring view , Context context ) {
        this.view = view;
        this.context = context;
    }
    @Override
    public void LoadData() {
        view.loadding();
        DataClient dataClient = APIutils.getData();
        Call<List<Dring>> callback = dataClient.GetDoUong();
        callback.enqueue(new Callback<List<Dring>>() {
            @Override
            public void onResponse(Call<List<Dring>> call, Response<List<Dring>> response) {
                data = (List<Dring>) response.body();
                adapter = new Adapter_dring(context,data);
                if(data.size()>0){
                    view.LoadData(data,adapter);
                    view.LoadSuccess("Đồ Uống",context);
                }else{
                    view.LoadError("Load Lỗi",context);

                }
                view.loadcomplete();
            }
            @Override
            public void onFailure(Call<List<Dring>> call, Throwable t) {
                view.loadcomplete();
            }
        });
    }

    @Override
    public void Search(String tenmonan) {
        view.loadding();
        DataClient dataClient = APIutils.getData();
        Call<List<Dring>> callback = dataClient.Search3(tenmonan);
        callback.enqueue(new Callback<List<Dring>>() {
            @Override
            public void onResponse(Call<List<Dring>> call, Response<List<Dring>> response) {
                data = (List<Dring>) response.body();
                adapter = new Adapter_dring(context,data);
                if(data.size()>0){
                    view.LoadData(data,adapter);
                }else{
                    view.LoadError("không tìm thấy",context);
                }
                view.loadcomplete();
            }

            @Override
            public void onFailure(Call<List<Dring>> call, Throwable t) {
                Log.d("bbb", "onFailure: ");
                view.loadcomplete();
            }
        });

    }
}
