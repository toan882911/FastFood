package com.example.doannhanh.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doannhanh.R;
import com.example.doannhanh.Retrofit2.APIutils;
import com.example.doannhanh.Retrofit2.DataClient;
import com.example.doannhanh.model.Cart;
import com.example.doannhanh.model.Table;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_table extends RecyclerView.Adapter {
    private Context context;
    private LayoutInflater inflater;
    TableActivity tableActivity;
    List<Table> data = Collections.emptyList();
    public Adapter_table(Context context, List<Table> data) {
        this.context = context;
        inflater =LayoutInflater.from(context);
        this.data = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_table,parent,false);
        Adapter_table.Myholder myholder = new Adapter_table.Myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final Adapter_table.Myholder myHolder = (Adapter_table.Myholder) viewHolder;
        final  Table current = data.get(position);
        myHolder.tvname.setText(current.getHoten());
        myHolder.tvban.setText("Bàn Số : "+current.getSoban());
        myHolder.tvsl.setText("Số Người : "+current.getSonguoi());
        myHolder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataClient dataClient = APIutils.getData();
                Call<String> callback = dataClient.DeleteTable(current.getId(),current.getSoban());
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result = response.body();
                        if(result.equals("success")){
                            Intent intent3 = new Intent(context,SetTable.class);
                            context.startActivity(intent3);
                        }else {
                            Intent intent3 = new Intent(context,SetTable.class);
                            context.startActivity(intent3);
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class Myholder extends RecyclerView.ViewHolder{
        ImageButton btndelete;
        TextView tvname,tvsl,tvban;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.ten);
            tvban = itemView.findViewById(R.id.tenban);
            btndelete = itemView.findViewById(R.id.delete);
            tvsl =itemView.findViewById(R.id.sl);
        }
    }

}
