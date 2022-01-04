package com.example.lcf.DaftarOrder.AdapterOrder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lcf.DaftarOrder.ModelOrder.DataModelOrder;
import com.example.lcf.R;

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
        holder.status.setText(dm.getStatus());
        holder.tglorder.setText(dm.getTglorder());

    }

    @Override
    public int getItemCount() {
        return listDataOrder.size();
    }

    public  class HolderData extends RecyclerView.ViewHolder{
        TextView count,Orderid,status,tglorder;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            count = itemView.findViewById(R.id.Total);
            Orderid = itemView.findViewById(R.id.Kode_Order);
            status = itemView.findViewById(R.id.status);
            tglorder = itemView.findViewById(R.id.tanggal_order);
        }
    }
}

