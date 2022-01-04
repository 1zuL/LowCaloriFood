package com.example.lcf.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lcf.Cart.Cart;
import com.example.lcf.Cart.Model.DataModel2;
import com.example.lcf.Model.DataModel;
import com.example.lcf.R;
import com.example.lcf.detail;

import java.util.ArrayList;
import java.util.List;

public class AdapterData extends  RecyclerView.Adapter<AdapterData.HolderData>{

    private Context ctx;
    private List<DataModel> listData;
    private String helo = "https://ws-tif.com/lcfp/food_ordering/";

    public AdapterData(Context ctx, List<DataModel> listData) {
        this.ctx = ctx;
        this.listData = listData;

    }


    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holder =  new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position);

        holder.tvId.setText(String.valueOf(dm.getIdproduk()));
        holder.tvNama.setText(dm.getNamaproduk());
        holder.tvDesc.setText(dm.getDeskripsi());
        holder.tvHarga.setText(dm.getHargaafter());



        Glide.with(ctx).load(helo+dm.getGambar()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.tvimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ctx, dm.getNamaproduk(), Toast.LENGTH_LONG).show();
                Intent intent =  new Intent(ctx, detail.class);
                intent.putExtra("id",String.valueOf(dm.getIdproduk()));
                intent.putExtra("foto_makanan", dm.getGambar());
                intent.putExtra("Nama_Makanan",dm.getNamaproduk());
                intent.putExtra("info_makanan", dm.getDeskripsi());
                intent.putExtra("harga_makanan",dm.getHargaafter());


                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setFilter(ArrayList<DataModel> filterModel){
        listData = new ArrayList<>();
        listData.addAll(filterModel);
        notifyDataSetChanged();
    }
    public  class HolderData extends RecyclerView.ViewHolder{
        TextView tvId,tvNama,tvDesc,tvHarga;
        ImageView tvimage;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvDesc = itemView.findViewById(R.id.desc);
            tvHarga = itemView.findViewById(R.id.harga);
            tvimage = (ImageView) itemView.findViewById(R.id.tvGambar);
        }

    }
}
