package com.example.doannhanh.view;

import android.content.Context;

import com.example.doannhanh.model.DoAn;

import java.util.List;

public interface IHome {
    void LoadData(List<DoAn> data, Adapter_home adapter);
    void LoadSuccess(String text, Context context);
    void LoadError(String text, Context context);
    void loadding();
    void loadcomplete();
}
