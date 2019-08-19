package com.example.doannhanh.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.doannhanh.R;
import com.example.doannhanh.presenter.IPDring;
import com.example.doannhanh.presenter.IPFood;
import com.example.doannhanh.presenter.PDring;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

public class Dring extends Fragment implements IDring,MainActivity.search {
    RecyclerView list;
    IPDring iFood;
    MainActivity main;
    ProgressBar progressBar;
    LinearLayoutManager mLayoutManager;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home,null);
        main.callback=this;
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main= (MainActivity) context;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        iFood = new PDring(this,getContext());
        iFood.LoadData();

    }
    public void anhxa(View view){
        list = (RecyclerView) view.findViewById(R.id.list_item);
        progressBar = view.findViewById(R.id.pro2);
    }
    @Override
    public void LoadData(List<com.example.doannhanh.model.Dring> data, Adapter_dring adapter) {
        mLayoutManager = new LinearLayoutManager(getContext());
        list.setAdapter(adapter);
        list.setLayoutManager(mLayoutManager);
    }

    @Override
    public void LoadSuccess(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoadError(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadding() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadcomplete() {
        progressBar.setVisibility(View.GONE);
    }
    public String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
    @Override
    public void Search(String chuoi) {
        iFood.Search(unAccent(chuoi));
    }
}
