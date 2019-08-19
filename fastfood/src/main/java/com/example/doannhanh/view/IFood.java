package com.example.doannhanh.view;

import android.content.Context;


import com.example.doannhanh.model.Food;

import java.util.List;

public interface IFood {
    void LoadData(List<Food> data, Adapter_food adapter);
    void LoadSuccess(String text, Context context);
    void LoadError(String text, Context context);
    void loadding();
    void loadcomplete();
}
