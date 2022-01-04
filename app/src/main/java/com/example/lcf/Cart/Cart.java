package com.example.lcf.Cart;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lcf.Activity.MainActivity;
import com.example.lcf.Adapter.AdapterData;
import com.example.lcf.Cart.Adapter.AdapterData2;
import com.example.lcf.Cart.Model.DataModel2;
import com.example.lcf.LoginRegister.DbContract;
import com.example.lcf.LoginRegister.Login;
import com.example.lcf.LoginRegister.VolleyConnection;
import com.example.lcf.R;
import com.example.lcf.SessionManager;
import com.example.lcf.confirmasiCheckout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart extends AppCompatActivity {

    private static final String TAG = Cart.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager linearlayoutManager;
    private List<DataModel2> listDataCart = new ArrayList<>();
    DataModel2 dataModel2;
    private String ALAMAT_DATA_CART = "https://ws-tif.com/lcfp/AplikasiMobileAPI/dataCart.php";
    private String ALAMAT_DATA_CART_TOTAL = "https://ws-tif.com/lcfp/AplikasiMobileAPI/hargatotal.php";
    SwipeRefreshLayout swipeRefreshLayout;
    TextView idOrang;
    private String nama;
    private String KEY_TOTAL = "TOTAL";
    private String KEY_ORDER = "ORDER";
    SessionManager sessionManager;
    String getId;
    TextView totalharga,orderidd;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        sessionManager = new SessionManager(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        idOrang = findViewById(R.id.idOrang);
        idOrang.setText(getId);
        totalharga = findViewById(R.id.totalHarga);
        orderidd = findViewById(R.id.orderidd);

        //swipeRefreshLayout = findViewById(R.id.swipeCart);

        recyclerView = findViewById(R.id.rv_data2);
        //linearlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //recyclerView.setLayoutManager(linearlayoutManager);
        dataCart();
        TotalHarga();

        checkout = findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                String orderidd2 = orderidd.getText().toString();
                String Totalharga2 = totalharga.getText().toString();
                if (!Totalharga2.equals("10000")) {
                    Intent intent = new Intent(Cart.this, confirmasiCheckout.class);
                    intent.putExtra(KEY_TOTAL, Totalharga2);
                    intent.putExtra(KEY_ORDER, orderidd2);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplication(),"Data Cart Kosong",Toast.LENGTH_SHORT);
                }
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplication(), "ERROR, TRY AGAIN !",Toast.LENGTH_SHORT);
                }

            }
        });

    }

    public void TotalHarga() {
        if (checkNetworkConnection()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ALAMAT_DATA_CART_TOTAL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                if (resp.equals("1")) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        String hargatotal = object.getString("hargatotal").trim();
                                        String orderid = object.getString("orderidd");
                                        totalharga.setText(hargatotal);
                                        orderidd.setText(orderid);

                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("idAcount", idOrang.getText().toString());

                    return params;
                }
            };

            VolleyConnection.getInstance(Cart.this).addToRequestQue(stringRequest);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    void dataCart() {
        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST, ALAMAT_DATA_CART, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response2) {

                //Toast.makeText(detail.this, response2, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response2);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        dataModel2 = new DataModel2();
                        JSONObject data = jsonArray.getJSONObject(i);
                        dataModel2.setIdproduk(data.getInt("idproduk"));
                        dataModel2.setNamaproduk(data.getString("namaproduk"));
                        dataModel2.setGambar(data.getString("gambar"));
                        int harga = data.getInt("hargaafter")*data.getInt("qty");
                        dataModel2.setHargaafter(harga);
                        dataModel2.setQty(data.getInt("qty"));

                        listDataCart.add(dataModel2);

                    }
                    linearlayoutManager = new LinearLayoutManager(Cart.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearlayoutManager);
                    adapter =  new AdapterData2(Cart.this,listDataCart);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Cart.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idAcount", idOrang.getText().toString());
                return params;
            }
        };
        rq.add(sr);
    }

}
