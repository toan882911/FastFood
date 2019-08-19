package com.example.doannhanh.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doannhanh.R;
import com.example.doannhanh.model.Dring;
import com.example.doannhanh.model.Food;
import com.example.doannhanh.util.SquareImageView;

import java.util.Collections;
import java.util.List;

public class Adapter_dring extends RecyclerView.Adapter{
    private Context context;
    private LayoutInflater inflater;
    List<Dring> data = Collections.emptyList();
    Adapter_dring adapter = this;
    public Adapter_dring(Context context, List<Dring> data) {
        this.context = context;
        inflater =LayoutInflater.from(context);
        this.data = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.custom_home,parent,false);
        Myholder myholder = new Myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final Myholder myHolder = (Myholder) viewHolder;
        final Dring current = data.get(position);
        myHolder.tvname.setText(current.getTenmonan());
        myHolder.tvgia.setText(current.getGiatien()+" vnđ");
        myHolder.tvgiamgia.setText(current.getGiamgia() + " %");
        Glide.with(context).load("http://abc12221.000webhostapp.com/"+current.getAnh()).into(myHolder.img);
        myHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,Detail.class);
                intent.putExtra("id",current.getId());
                intent.putExtra("tenmonan",current.getTenmonan());
                intent.putExtra("tenloai",current.getTenloai());
                intent.putExtra("noidung",current.getNoidung());
                intent.putExtra("anh",current.getAnh());
                intent.putExtra("giatien",current.getGiatien());
                intent.putExtra("giamgia",current.getGiamgia());
                intent.putExtra("yeuthich",current.getYeuthich());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class Myholder extends RecyclerView.ViewHolder{
        ImageButton imgbtn;
        TextView tvname,tvgia,tvgiamgia;
        SquareImageView img;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            img = (SquareImageView) itemView.findViewById(R.id.img);
            imgbtn = (ImageButton) itemView.findViewById(R.id.imgbtn);
            tvgia = (TextView) itemView.findViewById(R.id.gia);
            tvname = (TextView) itemView.findViewById(R.id.name);
            tvgiamgia = (TextView) itemView.findViewById(R.id.sale);
        }
    }

}
