package com.example.lcf.DaftarOrder.AdapterOrder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lcf.DaftarOrder.ModelOrder.DataModelOrder;
import com.example.lcf.KonfirmasiOrder;
import com.example.lcf.R;
import com.example.lcf.detail;

import java.util.List;

public class AdapterDataOrder extends RecyclerView.Adapter<AdapterDataOrder.HolderData>{

    private Context ctx;
    private List<DataModelOrder> listDataOrder;

    public AdapterDataOrder(Context ctx,List<DataModelOrder> listDataOrder){
        this.ctx = ctx;
        this.listDataOrder = listDataOrder;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.listdaftarorder, parent, false);
        AdapterDataOrder.HolderData holder =  new AdapterDataOrder.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModelOrder dm = listDataOrder.get(position);

        holder.count.setText(String.valueOf(dm.getCount()));
        holder.Orderid.setText(dm.getOrderid());
        holder.tglorder.setText(dm.getTglorder());
        if(!dm.getStatus().equals("Payment")){
            holder.button2.setVisibility(View.GONE);
            holder.status.setText(dm.getStatus());
        }else{
            holder.status.setVisibility(View.GONE);
        }
        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ctx, KonfirmasiOrder.class);
                intent.putExtra("orderid", dm.getOrderid());


                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDataOrder.size();
    }

    public  class HolderData extends RecyclerView.ViewHolder{
        TextView count,Orderid,status,tglorder;
        Button button2;
        public HolderData(@NonNull View itemView) {
            super(itemView);

            count = itemView.findViewById(R.id.Total);
            Orderid = itemView.findViewById(R.id.Kode_Order);
            status = itemView.findViewById(R.id.status);
            tglorder = itemView.findViewById(R.id.tanggal_order);
            button2 = itemView.findViewById(R.id.Buttonpembayaran);

        }
    }
}

