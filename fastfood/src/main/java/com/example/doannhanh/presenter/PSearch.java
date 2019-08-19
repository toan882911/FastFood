package com.example.doannhanh.presenter;

import android.content.Context;

import com.example.doannhanh.Retrofit2.APIutils;
import com.example.doannhanh.Retrofit2.DataClient;
import com.example.doannhanh.model.DoAn;
import com.example.doannhanh.model.Food;
import com.example.doannhanh.model.Search;
import com.example.doannhanh.util.Getdata_Home;
import com.example.doannhanh.view.Adapter_home;
import com.example.doannhanh.view.Adapter_search;
import com.example.doannhanh.view.IHome;
import com.example.doannhanh.view.ISearch;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class PSearch implements IPSearch{
//    ISearch view;
//    Context context;
//    String tenmonan;
//    Adapter_search adapter;
//    List<DoAn> data = new ArrayList<>();
//    public PSearch(ISearch view , Context context,String tenmonan) {
//        this.view = view;
//        this.context = context;
//        this.tenmonan =tenmonan;
//    }

//    @Override
//    public void LoadData() {
//        DataClient dataClient = APIutils.getData();
//        Call<List<DoAn>> callback = dataClient.Search(tenmonan);
//        callback.enqueue(new Callback<List<DoAn>>() {
//            @Override
//            public void onResponse(Call<List<DoAn>> call, Response<List<DoAn>> response) {
//                data = (List<DoAn>) response.body();
//                adapter = new Adapter_search(context,data);
//                if(data.size()>0){
//                    view.LoadData(data,adapter);
//                }else{
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DoAn>> call, Throwable t) {
//
//            }
//        });
//    }
//}
