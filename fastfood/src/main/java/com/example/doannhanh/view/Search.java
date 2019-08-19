package com.example.doannhanh.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.doannhanh.R;
import com.example.doannhanh.model.DoAn;
import com.example.doannhanh.model.Food;
import com.example.doannhanh.presenter.IPFood;
import com.example.doannhanh.presenter.IPSearch;

import java.util.List;

public class Search extends Fragment implements ISearch{
    RecyclerView list;
    IPSearch iSearch;
    LinearLayoutManager mLayoutManager;
    String tenmonan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home,null);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        anhxa(view);
        Bundle bundle = getArguments();
        tenmonan = bundle.getString("tenmonan");
//        if (getArguments() != null) {
//            tenmonan = getArguments().getString("tenmonan");
//        }
//        iSearch = new PSearch(this,getContext(),tenmonan);
//        iSearch.LoadData();

    }
    public void anhxa(View view){
        list = (RecyclerView) view.findViewById(R.id.list_item);
    }
    @Override
    public void LoadData(List<com.example.doannhanh.model.Search> data, Adapter_search adapter) {
        mLayoutManager = new LinearLayoutManager(getContext());
        list.setAdapter(adapter);
        list.setLayoutManager(mLayoutManager);
    }
    public static Search newInstance(String param1) {
        Search fragment = new Search();
        Bundle args = new Bundle();
        args.putString("tenmonan", param1);
        fragment.setArguments(args);
        return fragment;
    }
}
