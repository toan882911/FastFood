package com.example.doannhanh.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.doannhanh.R;
import com.example.doannhanh.Retrofit2.APIutils;
import com.example.doannhanh.Retrofit2.DataClient;
import com.example.doannhanh.model.Cart;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_cart extends RecyclerView.Adapter{
    private Context context;
    private LayoutInflater inflater;
    ITotal total;

    CartActivity cartActivity;
    List<Cart> data = Collections.emptyList();
    Integer tong=0,sl,tongtien;
    public Adapter_cart(Context context, List<Cart> data) {
        this.context = context;
        inflater =LayoutInflater.from(context);
        this.data = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_cart,parent,false);
        Myholder myholder = new Myholder(view);
        return myholder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final Myholder myHolder = (Myholder) viewHolder;
        final  Cart current = data.get(position);
        myHolder.tvname.setText(current.getTenmon());
        myHolder.tvtongtien.setText(current.getGiatien());
        myHolder.edtsl.setText(current.getSoluong());
        Glide.with(context).load("http://abc12221.000webhostapp.com/"+current.getAnh()).into(myHolder.img);
//        for(int i=0;i<data.size();i++){
//            tong+=Integer.parseInt(data.get(i).getTongtien());
//        }
//        total.total(tong);
        myHolder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total.loadding();
                DataClient dataClient = APIutils.getData();
                Call<String> callback = dataClient.DeleteFood(current.getId());
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result = response.body();
                        if(result.equals("success")){
                            Intent intent3 = new Intent(context,CartActivity.class);
                            context.startActivity(intent3);
                            total.loadcomplete();
                        }else {
                            Intent intent3 = new Intent(context,CartActivity.class);
                            context.startActivity(intent3);
                            total.loadcomplete();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
        myHolder.btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sl=Integer.parseInt(myHolder.edtsl.getText().toString());
                tongtien=sl*Integer.parseInt(current.getGiatien());
                tongtien=sl*Integer.parseInt(current.getGiatien());
                if(sl<=0){
                    Toast.makeText(context, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                }else if (sl>10){
                    Toast.makeText(context, "Số lượng không vượt quá 10", Toast.LENGTH_SHORT).show();
                }else {
                    total.loadding();
                    DataClient dataClient = APIutils.getData();
                    Call<String> callback =dataClient.UpdateFood(current.getId(),sl,tongtien);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if(result.equals("success")){
                                Intent intent3 = new Intent(context,CartActivity.class);
                                context.startActivity(intent3);
                                total.loadcomplete();
                            }else {
                                Intent intent3 = new Intent(context,CartActivity.class);
                                context.startActivity(intent3);
                                total.loadcomplete();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class Myholder extends RecyclerView.ViewHolder{
        ImageButton btndelete;
        TextView tvname,tvtongtien,tvtotal;
        ImageView img;
        EditText edtsl;
        Button btnsave;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            img =  itemView.findViewById(R.id.img);
            tvname = itemView.findViewById(R.id.tenmon);
            tvtongtien = itemView.findViewById(R.id.tongtien);
            edtsl = itemView.findViewById(R.id.soluong);
            btnsave = itemView.findViewById(R.id.btnsave);
            btndelete = itemView.findViewById(R.id.delete);
        }
    }

}
