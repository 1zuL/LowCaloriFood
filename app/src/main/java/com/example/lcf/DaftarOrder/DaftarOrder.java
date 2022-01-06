package com.example.lcf.DaftarOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lcf.Cart.Adapter.AdapterData2;
import com.example.lcf.Cart.Cart;
import com.example.lcf.Cart.Model.DataModel2;
import com.example.lcf.DaftarOrder.AdapterOrder.AdapterDataOrder;
import com.example.lcf.DaftarOrder.ModelOrder.DataModelOrder;
import com.example.lcf.LoginRegister.DbContract;
import com.example.lcf.LoginRegister.VolleyConnection;
import com.example.lcf.R;
import com.example.lcf.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaftarOrder extends AppCompatActivity {
    private static final String TAG = Cart.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager linearlayoutManager;
    private List<DataModelOrder> listDataCart = new ArrayList<>();

    SessionManager sessionManager;
    DataModelOrder dataModelOrder;
    String getId;
    ImageView backtocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_order);
        sessionManager = new SessionManager(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        recyclerView = findViewById(R.id.detailorder);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        backtocart = findViewById(R.id.backtocart);
        backtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(DaftarOrder.this, Cart.class);
                startActivity(intent);
            }
        });
        dataCart();
    }
    void dataCart() {
        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST, DbContract.ALAMAT_DATA_Order, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response2) {

                //Toast.makeText(detail.this, response2, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response2);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        dataModelOrder = new DataModelOrder();
                        JSONObject data = jsonArray.getJSONObject(i);
                        dataModelOrder.setOrderid(data.getString("orderid"));
                        dataModelOrder.setStatus(data.getString("status"));
                        dataModelOrder.setTglorder(data.getString("tglorder"));
                        dataModelOrder.setCount(data.getInt("count"));

                        listDataCart.add(dataModelOrder);

                    }
                    linearlayoutManager = new LinearLayoutManager(DaftarOrder.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearlayoutManager);
                    adapter =  new AdapterDataOrder(DaftarOrder.this,listDataCart);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DaftarOrder.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idAcount", getId);
                return params;
            }
        };
        rq.add(sr);
    }

}