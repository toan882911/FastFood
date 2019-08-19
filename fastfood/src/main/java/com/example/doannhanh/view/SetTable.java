package com.example.doannhanh.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.doannhanh.R;
import com.example.doannhanh.Retrofit2.APIutils;
import com.example.doannhanh.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetTable extends AppCompatActivity {
    private Dialog dialog;
    SharedPreferences sharedPreferences;
    String id, name, image;
    ImageView banso1, banso2, banso3, banso4, banso5, banso6, banso7, banso8, banso9, banso10, back,load;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10;
    Integer result;
    ProgressBar progressBar;
    ArrayList<ImageView> mang;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_table);
        load= findViewById(R.id.load);
        sharedPreferences = getSharedPreferences("data_login", MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");
        name = sharedPreferences.getString("name", "");
        image = sharedPreferences.getString("image", "");
        if (id != "" && image != "" && name != "") {
            anhxa();
            progressBar.setVisibility(View.VISIBLE);
            addmang();
            checktable();
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            load.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SetTable.this,TableActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            showAlertDialog2();

        }

    }

    private void showAlertDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo ! ");
        builder.setMessage("Cần Phải Đăng Nhập ");
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
                Intent intent3 = new Intent(SetTable.this, LoginActivity.class);
                startActivity(intent3);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void checktable() {
        GetTable(1, banso1,2);
        GetTable(2, banso2,2);
        GetTable(3, banso3,2);
        GetTable(4, banso4,2);
        GetTable(5, banso5,2);
        GetTable(6, banso6,6);
        GetTable(7, banso7,6);
        GetTable(8, banso8,6);
        GetTable(9, banso9,6);
        GetTable(10, banso10,6);
    }

    private void GetTable(final Integer soban, final ImageView table,final Integer songuoi) {
        DataClient dataClient = APIutils.getData();
        Call<Integer> callback = dataClient.CheckTable(soban);
        callback.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                result = response.body();
                if (result == 1) {
                    progressBar.setVisibility(View.GONE);
                    table.setImageResource(R.drawable.table2);
                    table.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showAlertDialog();
                        }
                    });
                } else {
                    progressBar.setVisibility(View.GONE);
                    table.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(SetTable.this, SetTableActitivy.class);
                            intent.putExtra("soban", soban);
                            intent.putExtra("songuoi", songuoi);
                            startActivity(intent);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

    }

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bàn Này Đã Được Đặt ");
        builder.setMessage("Mời Bạn Chọn Bàn Khác");
        builder.setCancelable(false);
        builder.setPositiveButton("Không Đồng Ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        builder.setNegativeButton("Đồng Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void addmang() {
        ImageviewClick(banso1);
        ImageviewClick(banso2);
        ImageviewClick(banso3);
        ImageviewClick(banso4);
        ImageviewClick(banso5);
        ImageviewClick(banso6);
        ImageviewClick(banso7);
        ImageviewClick(banso8);
        ImageviewClick(banso9);
        ImageviewClick(banso10);
    }

    private void ImageviewClick(ImageView image) {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SetTable.this, "Please Wait", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void TextviewClick(final TextView text) {
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetTable.this, text.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void anhxa() {
        progressBar = findViewById(R.id.pro);
        banso1 = findViewById(R.id.table1);
        banso2 = findViewById(R.id.table2);
        banso3 = findViewById(R.id.table3);
        banso4 = findViewById(R.id.table4);
        banso5 = findViewById(R.id.table5);
        banso6 = findViewById(R.id.table6);
        banso7 = findViewById(R.id.table7);
        banso8 = findViewById(R.id.table8);
        banso9 = findViewById(R.id.table9);
        banso10 = findViewById(R.id.table10);
        back = findViewById(R.id.back);
        tv1 = findViewById(R.id.tvtable1);
        tv2 = findViewById(R.id.tvtable2);
        tv3 = findViewById(R.id.tvtable3);
        tv4 = findViewById(R.id.tvtable4);
        tv5 = findViewById(R.id.tvtable5);
        tv6 = findViewById(R.id.tvtable6);
        tv7 = findViewById(R.id.tvtable7);
        tv8 = findViewById(R.id.tvtable8);
        tv9 = findViewById(R.id.tvtable9);
        tv10 = findViewById(R.id.tvtable10);
    }
}
