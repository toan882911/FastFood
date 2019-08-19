package com.example.doannhanh.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.doannhanh.R;
import com.example.doannhanh.Retrofit2.APIutils;
import com.example.doannhanh.Retrofit2.DataClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetTableActitivy extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    int nam,thang,ngay,gio,phut,giay,soban,songuoi;
    String id,name,image,thoigian,phone,phone2;
    EditText edtname,edtphone,edttablename;
    TextView tvdate,tvtime,number;
    Button btn;
    ImageView back;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_table_actitivy);
        sharedPreferences = getSharedPreferences("data_login",MODE_PRIVATE);
        id = sharedPreferences.getString("id","");
        name = sharedPreferences.getString("name","");
        image = sharedPreferences.getString("image","");
        Intent intent = getIntent();
        soban = intent.getIntExtra("soban",soban);
        songuoi =intent.getIntExtra("songuoi",songuoi);
        anhxa();
        edtname.setText(name);
        number.setText(String.valueOf(songuoi));
        edttablename.setText(String.valueOf(soban));
        tvdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonngay();

            }
        });
        tvtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chongio();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone2=edtphone.getText().toString();
                if(edtname.getText().toString() !="" && edtphone.getText().length()>0 && edttablename.getText().toString().length() > 0 && tvdate.getText().toString() !=""&&tvtime.getText().toString()!=""){
                    if(phone2.length()<10){
                        Toast.makeText(SetTableActitivy.this, "Nhập Sai Kiểu Số Điện Thoại", Toast.LENGTH_SHORT).show();
                        Toast.makeText(SetTableActitivy.this, "Số Điện Thoại Có 10 Số", Toast.LENGTH_SHORT).show();
                    }else if(phone2.length()>10){
                        Toast.makeText(SetTableActitivy.this, "Số Điện Thoại Có 10 Số", Toast.LENGTH_SHORT).show();
                    }else {
                        progressBar.setVisibility(View.VISIBLE);
                        phone = edtphone.getText().toString();
                        thoigian = tvdate.getText().toString()+" "+tvtime.getText().toString();
                        final DataClient dataClient = APIutils.getData();
                        Call<String> callback = dataClient.SetTable(name,id,soban,songuoi,phone,thoigian);
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String result = response.body();
                                if(result.equals("success")){
                                    Call<String> callback2=dataClient.UpdateTable(soban);
                                    callback2.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            String result=response.body();
                                            progressBar.setVisibility(View.GONE);
                                            if(result.equals("success")){

                                                Toast.makeText(SetTableActitivy.this, "Đặt Bàn Thành Công", Toast.LENGTH_SHORT).show();
                                                Intent intent2=new Intent(SetTableActitivy.this, SetTable.class);;
                                                startActivity(intent2);
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {

                                        }
                                    });
                                }else{
                                    Toast.makeText(SetTableActitivy.this, "Mời nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }


                }else {
                    Toast.makeText(SetTableActitivy.this, "Mời nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void chongio(){
        final Calendar calendar = Calendar.getInstance();
        gio = calendar.get(calendar.HOUR_OF_DAY);
        phut = calendar.get(calendar.MINUTE);
        TimePickerDialog datePickerDialog= new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                calendar.set(0,0,0,hourOfDay,minute);
                tvtime.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },gio,phut,true);
        datePickerDialog.show();
    }
    private void chonngay(){
        final Calendar calendar = Calendar.getInstance();
        nam = calendar.get(calendar.YEAR);
        thang = calendar.get(calendar.MONTH);
        ngay = calendar.get(calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                calendar.set(year,month,dayOfMonth);
                tvdate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    private void anhxa() {
        back =findViewById(R.id.back);
        edtname = findViewById(R.id.name);
        edtphone = findViewById(R.id.phone);
        edttablename = findViewById(R.id.tablename);
        tvdate =findViewById(R.id.date);
        tvtime = findViewById(R.id.time);
        number = findViewById(R.id.number);
        btn = findViewById(R.id.btncontinue);
        progressBar = findViewById(R.id.pro);
    }
}
