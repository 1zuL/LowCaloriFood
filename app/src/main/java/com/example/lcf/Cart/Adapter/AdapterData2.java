package com.example.lcf.Cart.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lcf.Activity.MainActivity;
import com.example.lcf.Cart.Cart;
import com.example.lcf.Cart.Model.DataModel2;
import com.example.lcf.R;
import com.example.lcf.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterData2 extends  RecyclerView.Adapter<AdapterData2.HolderData>{

    private Context ctx;
    private List<DataModel2> listData2;
    private String helo = "https://ws-tif.com/lcfp/food_ordering/";
    String getId;
    SessionManager sessionManager;


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

        Glide.with(ctx).load(helo + dm.getGambar()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL)
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
        holder.buttondeletecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ctx)
                        .setIcon(R.drawable.lcf)
                        .setTitle("Low Calory Food")
                        .setMessage("Apakah kamu yakin Untuk Menghapus Data")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String sidproduk = String.valueOf(dm.getIdproduk());
                                sessionManager = new SessionManager(ctx);
                                HashMap<String, String> user = sessionManager.getUserDetail();
                                getId = user.get(sessionManager.ID);
                                dataCart(getId,sidproduk);
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return listData2.size();
    }

    public  class HolderData extends RecyclerView.ViewHolder{
        TextView idproduk,namaproduk,qty,hargaafter;
        ImageView gambar;
        Button buttondeletecart;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            idproduk = itemView.findViewById(R.id.idproduk);
            namaproduk = itemView.findViewById(R.id.namaproduk);
            qty = itemView.findViewById(R.id.qty);
            hargaafter = itemView.findViewById(R.id.hargaafter);
            gambar = (ImageView) itemView.findViewById(R.id.gambar);
            buttondeletecart = itemView.findViewById(R.id.DeleteCart);

        }
    }
    public void dataCart(String IdAcount, String idproduk) {
        RequestQueue rq = Volley.newRequestQueue(ctx);
        StringRequest sr = new StringRequest(Request.Method.POST, "https://ws-tif.com/lcfp/AplikasiMobileAPI/hapuscart.php",
                new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response2) {

                //Toast.makeText(detail.this, response2, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response2);
                    String resp = jsonObject.getString("server_response");
                    if (resp.equals("[{\"status\":\"OK\"}]")) {
                        Toast.makeText(ctx,"Data Terhapus", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ctx,Cart.class);
                        ctx.startActivity(intent);
                        ((Activity)ctx).finish();
                    }else{
                        Toast.makeText(ctx,"Data Tidak Terhapus", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", IdAcount);
                params.put("idproduknya",idproduk);
                return params;
            }
        };
        rq.add(sr);
    }
    public void Delete(int item){
        listData2.remove(item);
        notifyItemRemoved(item);
    }


}
