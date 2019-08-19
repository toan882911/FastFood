package com.example.doannhanh.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.doannhanh.R;

public class Order extends AppCompatActivity {
    ImageView imgfood;
    private Dialog dialog;
    Button btnnext;
    int total2;
    int giatienint,giamgiaint,giamgiaint2;
    ImageView btnback;
    TextView name,gia,giamgia,total;
    String tenmonan,tenloai,nd,anh,giatien,giamgia2,yeuthich;
    SharedPreferences sharedPreferences;
    String id,name2,image;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_success);
        anhxa();
        sharedPreferences = getSharedPreferences("data_login",MODE_PRIVATE);
        id = sharedPreferences.getString("id","");
        name2 = sharedPreferences.getString("name","");
        image = sharedPreferences.getString("image","");
        Intent intent = getIntent();
        tenmonan = intent.getStringExtra("tenmonan");
        tenloai = intent.getStringExtra("tenloai");
        nd = intent.getStringExtra("noidung");
        anh = intent.getStringExtra("anh");
        giatien = intent.getStringExtra("giatien");
        giamgia2 = intent.getStringExtra("giamgia");
        yeuthich = intent.getStringExtra("yeuthich");
        try{
            giatienint = Integer.parseInt(giatien);
            giamgiaint = Integer.parseInt(giamgia2);
        }catch (Exception e){
            giatienint =0;
            giamgiaint=0;
        }

        giamgiaint2 = giatienint*giamgiaint/100;
        total2 = giatienint - giamgiaint2;
        name.setText(tenmonan);
        gia.setText(giatien );
        giamgia.setText(giamgia2+ " %");
        Glide.with(imgfood.getContext()).load("http://abc12221.000webhostapp.com/"+anh).into(imgfood);
        total.setText(String.valueOf(total2));
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id!="" && image!="" && name2!=""){
                    anhxa();

                    Intent intent2=new Intent(Order.this, Buy.class);
                    intent2.putExtra("tenmonan",tenmonan);
                    intent2.putExtra("anh",anh);
                    intent2.putExtra("giatien",String.valueOf(total2));
                    startActivity(intent2);
                }else {
                    showAlertDialog();

                }

            }
        });


    }
    public void showAlertDialog(){
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

                Intent intent3 = new Intent(Order.this,LoginActivity.class);
                intent3.putExtra("order","order");
                intent3.putExtra("tenmonan",tenmonan);
                intent3.putExtra("tenloai",tenloai);
                intent3.putExtra("noidung",nd);
                intent3.putExtra("anh",anh);
                intent3.putExtra("giatien",giatien);
                intent3.putExtra("giamgia",giamgia2);
                intent3.putExtra("yeuthich",yeuthich);
                startActivity(intent3);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    public void anhxa(){
        btnback = findViewById(R.id.back);
        imgfood = findViewById(R.id.imgfood);
        name = findViewById(R.id.textviewmon2);
        gia=findViewById(R.id.textviewgia2);
        giamgia=findViewById(R.id.textviewgiam2);
        total=findViewById(R.id.total);
        btnnext=findViewById(R.id.next);
    }
    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
