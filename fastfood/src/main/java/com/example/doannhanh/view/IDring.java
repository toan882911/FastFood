package com.example.doannhanh.view;

import android.content.Context;


import com.example.doannhanh.model.Dring;

import java.util.List;

public interface IDring {
    void LoadData(List<Dring> data, Adapter_dring adapter);
    void LoadSuccess(String text, Context context);
    void LoadError(String text, Context context);
    void loadding();
    void loadcomplete();
}
