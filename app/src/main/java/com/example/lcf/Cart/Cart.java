package com.example.lcf.Cart;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lcf.Adapter.AdapterData;
import com.example.lcf.Cart.Adapter.AdapterData2;
import com.example.lcf.Cart.Model.DataModel2;
import com.example.lcf.R;
import com.example.lcf.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager linearlayoutManager;
    private List<DataModel2> listDataCart = new ArrayList<>();
    DataModel2 dataModel2;
    private String ALAMAT_DATA_CART = "https://ws-tif.com/lcfp/AplikasiMobileAPI/dataCart.php";
    private String ALAMAT_DATA_CART_TOTAL = "https://ws-tif.com/lcfp/AplikasiMobileAPI/totalharga.php";
    SwipeRefreshLayout swipeRefreshLayout;
    TextView idOrang;
    private String nama;
    private String KEY_NAME = "NAMA";
    SessionManager sessionManager;
    String getId;

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
        //swipeRefreshLayout = findViewById(R.id.swipeCart);

        recyclerView = findViewById(R.id.rv_data2);
        //linearlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //recyclerView.setLayoutManager(linearlayoutManager);
        dataCart();
        totalharga();



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
                        dataModel2.setHargaafter(data.getInt("hargaafter"));
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
    void totalharga(){
        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST, ALAMAT_DATA_CART_TOTAL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response2) {
                Toast.makeText(Cart.this, response2, Toast.LENGTH_SHORT).show();


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
