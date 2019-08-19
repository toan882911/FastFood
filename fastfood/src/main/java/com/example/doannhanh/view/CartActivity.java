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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    Adapter_cart adapter;
    RecyclerView list;
    SharedPreferences sharedPreferences;
    LinearLayoutManager mLayoutManager;
    String id, name, image;
    Button btn;
    Adapter_cart adapter_cart;
    TextView tvtotal;
    ITotal total;
    ProgressBar progressBar;
    List<Cart> data = new ArrayList<>();
    Integer tong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        sharedPreferences = getSharedPreferences("data_login", MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");
        name = sharedPreferences.getString("name", "");
        image = sharedPreferences.getString("image", "");
        list = findViewById(R.id.listview);
        tvtotal = findViewById(R.id.total);
        btn = findViewById(R.id.cart);
        progressBar = findViewById(R.id.pro);
        progressBar.setVisibility(View.VISIBLE);
        total = new ITotal() {
            @Override
            public void total(Integer total) {

            }

            @Override
            public void load() {

            }

            @Override
            public void loadding() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void loadcomplete() {
                progressBar.setVisibility(View.GONE);
            }
        };
        if (id != "" && image != "" && image != "") {
            DataClient dataClient = APIutils.getData();
            Call<List<Cart>> callback = dataClient.GetCart(id);
            callback.enqueue(new Callback<List<Cart>>() {
                @Override
                public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                    progressBar.setVisibility(View.GONE);
                    data = response.body();
                    for (int i = 0; i <data.size(); i++) {
                        if(i==0){
                            tong = Integer.parseInt(data.get(0).getTongtien());
                        }else {
                            tong += Integer.parseInt(data.get(i).getTongtien());
                        }
                    }
                    tvtotal.setText(String.valueOf(tong));
                    adapter = new Adapter_cart(CartActivity.this, data);
                    adapter.total = total;
                    mLayoutManager = new LinearLayoutManager(CartActivity.this);
                    list.setAdapter(adapter);
                    list.setLayoutManager(mLayoutManager);
                }
                @Override
                public void onFailure(Call<List<Cart>> call, Throwable t) {
                }
            });
        } else {
            showAlertDialog();

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
                Intent intent3 = new Intent(CartActivity.this, LoginActivity.class);
                startActivity(intent3);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

//    @Override
//    public void total(Integer total) {
//        tvtotal.setText(String.valueOf(total));
//    }

    //    public void total(Integer total){
//        tvtotal.setText(String.valueOf(total));
//    }


}
