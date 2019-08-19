package com.example.doannhanh.presenter;

import android.content.Context;
import android.util.Log;

import com.example.doannhanh.Retrofit2.APIutils;
import com.example.doannhanh.Retrofit2.DataClient;
import com.example.doannhanh.model.DoAn;
import com.example.doannhanh.model.Food;
import com.example.doannhanh.view.Adapter_food;
import com.example.doannhanh.view.Adapter_home;
import com.example.doannhanh.view.IFood;
import com.example.doannhanh.view.IHome;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PFood implements IPFood {
    Context context;
    IFood view;
    Adapter_food adapter;
    List<Food> data = new ArrayList<>();
    public PFood(IFood view , Context context ) {
        this.view = view;
        this.context = context;
    }
    @Override
    public void LoadData() {
        view.loadding();
        DataClient dataClient = APIutils.getData();
        Call<List<Food>> callback = dataClient.GetDoAn();
        callback.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                data = (List<Food>) response.body();
                adapter = new Adapter_food(context,data);
                if(data.size()>0){
                    view.LoadData(data,adapter);
                    view.loadcomplete();
                }else{
                    view.LoadError("Load Lỗi",context);

                }
                view.loadcomplete();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                view.loadcomplete();
            }
        });
    }

    @Override
    public void Search(String tenmonan) {
        view.loadding();
        DataClient dataClient = APIutils.getData();
        Call<List<Food>> callback = dataClient.Search2(tenmonan);
        callback.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                data = (List<Food>) response.body();
                adapter = new Adapter_food(context,data);
                if(data.size()>0){
                    view.LoadData(data,adapter);
                }else{
                    view.LoadError("không tìm thấy",context);
                }
                view.loadcomplete();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Log.d("bbb", "onFailure: ");
                view.loadcomplete();
            }
        });
    }
}
