package com.example.doannhanh.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.doannhanh.R;
import com.example.doannhanh.Retrofit2.APIutils;
import com.example.doannhanh.Retrofit2.DataClient;
import com.example.doannhanh.model.Cart;
import com.example.doannhanh.model.Table;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableActivity extends AppCompatActivity {
    Adapter_table adapter;
    RecyclerView list;
    TextView tvtrong;
    SharedPreferences sharedPreferences;
    LinearLayoutManager mLayoutManager;
    String id, name, image;
    Button table;
    ProgressBar progressBar;
    List<Table> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        list = findViewById(R.id.listview);
        tvtrong = findViewById(R.id.tvtrong);

        sharedPreferences = getSharedPreferences("data_login", MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");
        name = sharedPreferences.getString("name", "");
        image = sharedPreferences.getString("image", "");
        table = findViewById(R.id.table);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(TableActivity.this, MainActivity.class);;
                startActivity(intent2);
            }
        });
        if (id != "" && image != "" && image != "") {
            DataClient dataClient = APIutils.getData();
            Call<List<Table>> callback = dataClient.GetTable(id);
            callback.enqueue(new Callback<List<Table>>() {
                @Override
                public void onResponse(Call<List<Table>> call, Response<List<Table>> response) {
                    data = response.body();
                    if (data != null) {
                        adapter = new Adapter_table(TableActivity.this, data);
                        mLayoutManager = new LinearLayoutManager(TableActivity.this);
                        list.setAdapter(adapter);
                        list.setLayoutManager(mLayoutManager);
                    }else{
                        tvtrong.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onFailure(Call<List<Table>> call, Throwable t) {

                }
            });
        } else {
            showAlertDialog();

        }
    }

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo !  ");
        builder.setMessage("Cần Phải Đăng Nhập");
        builder.setCancelable(false);
        builder.setPositiveButton("Không Đồng Ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Đồng Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent3 = new Intent(TableActivity.this, LoginActivity.class);
                startActivity(intent3);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
