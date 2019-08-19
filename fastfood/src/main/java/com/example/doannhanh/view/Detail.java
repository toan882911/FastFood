package com.example.doannhanh.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.doannhanh.R;
import com.example.doannhanh.Retrofit2.APIutils;
import com.example.doannhanh.Retrofit2.DataClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Detail extends AppCompatActivity {
    ImageView img;
    ImageButton imgbtn;
    Button btn_cart;
    Context context;
    String id, name2, image;
    Integer id3,yeuthich3;
    ProgressBar progressBar;
    TextView name, gia, mota, giamgia1, danhmuc, yeuthich2, labelname;
    FloatingActionButton btn_order, btn_map, btn_yeuthich, btn_khongthich;
    String tenmonan, tenloai, nd, anh, giatien, giamgia, yeuthich;
    TextView text;
    SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        anhxa();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ImageButton imgbtn = (ImageButton) findViewById(R.id.back);

        sharedPreferences = getSharedPreferences("data_login", MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");
        name2 = sharedPreferences.getString("name", "");
        image = sharedPreferences.getString("image", "");


        Intent intent = getIntent();
        String id2 = intent.getStringExtra("id");
        id3 = Integer.parseInt(id2);
        tenmonan = intent.getStringExtra("tenmonan");
        tenloai = intent.getStringExtra("tenloai");
        nd = intent.getStringExtra("noidung");
        anh = intent.getStringExtra("anh");
        giatien = intent.getStringExtra("giatien");
        giamgia = intent.getStringExtra("giamgia");
        yeuthich = intent.getStringExtra("yeuthich");

        name.setText(tenmonan);
        labelname.setText(tenmonan);
        danhmuc.setText(tenloai);
        gia.setText(giatien + " vnđ");
        mota.setText(nd);
        giamgia1.setText(giamgia + " %");
        yeuthich2.setText(yeuthich);
        Glide.with(img.getContext()).load("http://abc12221.000webhostapp.com/" + anh).into(img);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Detail.this, Order.class);

                intent2.putExtra("tenmonan", tenmonan);
                intent2.putExtra("tenloai", tenloai);
                intent2.putExtra("noidung", nd);
                intent2.putExtra("anh", anh);
                intent2.putExtra("giatien", giatien);
                intent2.putExtra("giamgia", giamgia);
                intent2.putExtra("yeuthich", yeuthich);

                startActivity(intent2);
            }
        });

        check();
        btn_khongthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id != "" && image != "" && name2 != "") {
                    btn_yeuthich.show();
                    btn_khongthich.hide();
                     DataClient dataClient = APIutils.getData();
                     Call<String> callback = dataClient.Like("like", id3,id);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            DataClient dataClient = APIutils.getData();
                            Call<String> callback2 = dataClient.GetLike(id3);
                            callback2.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    String result2 = response.body();
                                    yeuthich2.setText(result2);
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                } else {
                    showAlertDialog();
                }
            }
        });
        btn_yeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Detail.this, "Bạn đã yêu thích rồi ", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void check() {
        progressBar.setVisibility(View.VISIBLE);
        if (id != "" && image != "" && name2 != "") {
            final DataClient dataClient = APIutils.getData();
            Call<String> callback = dataClient.CheckLike(id3,id);
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String result  = response.body();
                    if(result.equals("success")){
                        btn_yeuthich.show();
                        btn_khongthich.hide();
                        Call<String> callback2 = dataClient.GetLike(id3);
                        callback2.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String result2 = response.body();
                                yeuthich2.setText(result2);
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }else {
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo ! ");
        builder.setMessage("Cần Phải Đăng Nhập ");
        builder.setCancelable(false);
        builder.setPositiveButton("Không Đồng Ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        builder.setNegativeButton("Đồng Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent3 = new Intent(Detail.this, LoginActivity.class);
                startActivity(intent3);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void fragment(Fragment ft) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentview, ft);
        fragmentTransaction.commit();
    }

    private void anhxa() {
        progressBar = findViewById(R.id.pro);
        btn_cart = findViewById(R.id.cart);
        img = (ImageView) findViewById(R.id.img);

        btn_yeuthich = (FloatingActionButton) findViewById(R.id.like);
        btn_khongthich = findViewById(R.id.dislike);
//        text=(TextView) findViewById(R.id.noidung);
        imgbtn = (ImageButton) findViewById(R.id.back);
        name = (TextView) findViewById(R.id.name);
        labelname = (TextView) findViewById(R.id.labelname);
        gia = (TextView) findViewById(R.id.gia);
        mota = (TextView) findViewById(R.id.mota);
        giamgia1 = findViewById(R.id.giamgia);
        yeuthich2 = (TextView) findViewById(R.id.yeuthich);
        danhmuc = (TextView) findViewById(R.id.danhmuc);
    }

    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}
