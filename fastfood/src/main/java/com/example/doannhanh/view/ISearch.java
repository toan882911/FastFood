package com.example.doannhanh.view;

import com.example.doannhanh.model.DoAn;
import com.example.doannhanh.model.Food;
import com.example.doannhanh.model.Search;

import java.util.List;

public interface ISearch {
    void LoadData(List<Search> data, Adapter_search adapter);
}
