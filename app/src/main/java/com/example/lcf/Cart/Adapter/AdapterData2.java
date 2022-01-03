package com.example.lcf.Cart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lcf.Cart.Model.DataModel2;
import com.example.lcf.R;

import java.util.List;

public class AdapterData2 extends  RecyclerView.Adapter<AdapterData2.HolderData>{

    private Context ctx;
    private List<DataModel2> listData2;
    private String helo = "https://ws-tif.com/lcfp/food_ordering/";

    public AdapterData2(Context ctx, List<DataModel2> listData2) {
        this.ctx = ctx;
        this.listData2 = listData2;

    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        HolderData holder =  new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel2 dm = listData2.get(position);

        holder.idproduk.setText(String.valueOf(dm.getIdproduk()));
        holder.namaproduk.setText(dm.getNamaproduk());
        holder.qty.setText(String.valueOf(dm.getQty()));
        holder.hargaafter.setText(String.valueOf(dm.getHargaafter()));

        Glide.with(ctx).load(helo+dm.getGambar()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.gambar);
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ctx, dm.getNamaproduk(), Toast.LENGTH_LONG).show();
                Intent intent =  new Intent(ctx, detail.class);
                intent.putExtra("id",String.valueOf(dm.getIdproduk()));
                intent.putExtra("foto_makanan", dm.getGambar());
                intent.putExtra("Nama_Makanan",dm.getNamaproduk());
                intent.putExtra("info_makanan", dm.getQty());
                intent.putExtra("harga_makanan",dm.getHargaafter());


                ctx.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return listData2.size();
    }

    public  class HolderData extends RecyclerView.ViewHolder{
        TextView idproduk,namaproduk,qty,hargaafter;
        ImageView gambar;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            idproduk = itemView.findViewById(R.id.idproduk);
            namaproduk = itemView.findViewById(R.id.namaproduk);
            qty = itemView.findViewById(R.id.qty);
            hargaafter = itemView.findViewById(R.id.hargaafter);
            gambar = (ImageView) itemView.findViewById(R.id.gambar);


        }
    }
}
