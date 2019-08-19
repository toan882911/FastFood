package com.example.doannhanh.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.doannhanh.R;
import com.example.doannhanh.Retrofit2.APIutils;
import com.example.doannhanh.Retrofit2.DataClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Buy extends AppCompatActivity {
    EditText edtname, edtaddress, edtphone, edtnote, edtsl;
    Button btnbuy;
    ImageView btnback;
    Integer tongtien2, soluong2;
    String phone;
    ProgressBar progressBar;
    String id, name, image;
    SharedPreferences sharedPreferences;
    String tenmon, giatien, hoten, tongtien, diachi, ghichu, sdt, anh, soluong;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_buy);
        anhxa();
        sharedPreferences = getSharedPreferences("data_login", MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");
        name = sharedPreferences.getString("name", "");
        image = sharedPreferences.getString("image", "");
        Intent intent = getIntent();
        tenmon = intent.getStringExtra("tenmonan");
        giatien = intent.getStringExtra("giatien");
        anh = intent.getStringExtra("anh");
        edtname.setText(name);
        hoten = edtname.getText().toString();
        diachi = edtaddress.getText().toString();
        ghichu = edtnote.getText().toString();
        sdt = edtphone.getText().toString();


        btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = edtphone.getText().toString();
                if (edtphone.getText().toString().length() > 0 && edtaddress.getText().toString().length() > 0 && edtname.getText().toString().length() > 0) {
                    if (phone.length() < 10) {
                        Toast.makeText(Buy.this, "Nhập Sai Kiểu Số Điện Thoại", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Buy.this, "Số Điện Thoại Có 10 Số", Toast.LENGTH_SHORT).show();
                    } else if (phone.length() > 10) {
                        Toast.makeText(Buy.this, "Số Điện Thoại Có 10 Số", Toast.LENGTH_SHORT).show();
                    } else {
                        loadding();
                        DataClient dataClient = APIutils.getData();
                        Call<String> callback = dataClient.Order(id, tenmon, anh, giatien, giatien, edtname.getText().toString(), edtaddress.getText().toString(), edtphone.getText().toString(), edtnote.getText().toString());
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String result = response.body();
                                if (result.equals("success")) {
                                    Intent intent3 = new Intent(Buy.this, CartActivity.class);
                                    startActivity(intent3);
                                    loadcomplete();
                                } else {
                                    Intent intent3 = new Intent(Buy.this, CartActivity.class);
                                    startActivity(intent3);
                                    loadcomplete();
                                }
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                            }
                        });
                    }

                } else {
                    loadcomplete();
                    Toast.makeText(Buy.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }


            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        edtname = (EditText) findViewById(R.id.name);
        edtaddress = (EditText) findViewById(R.id.address);
        edtphone = (EditText) findViewById(R.id.phone);
        edtnote = (EditText) findViewById(R.id.note);
        btnbuy = (Button) findViewById(R.id.btnbuy);
        progressBar = findViewById(R.id.pro);
        btnback = (ImageView) findViewById(R.id.back);
    }

    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public void loadding() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void loadcomplete() {
        progressBar.setVisibility(View.GONE);
    }
}
