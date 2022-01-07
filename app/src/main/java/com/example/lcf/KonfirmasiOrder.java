package com.example.lcf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.lcf.DaftarOrder.AdapterOrder.AdapterDataOrder;
import com.example.lcf.DaftarOrder.DaftarOrder;
import com.example.lcf.DaftarOrder.ModelOrder.DataModelOrder;
import com.example.lcf.LoginRegister.VolleyConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class KonfirmasiOrder extends AppCompatActivity {

    TextView koderorder,metode4,norek,date2,idakunn;
    Button kirim2;
    String getId;
    EditText nama;
    ImageView btnback;

    SessionManager sessionManager;
    private String ALAMAT_DATA_NOREK = "https://ws-tif.com/lcfp/AplikasiMobileAPI/rekeninglist.php";
    private String ALAMAT_DATA_KONFORDER = "https://ws-tif.com/lcfp/AplikasiMobileAPI/konfirmasiOrder.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_order);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        idakunn = findViewById(R.id.idakun);
        idakunn.setText(getId);
        koderorder = findViewById(R.id.kodeorder);
        metode4 = findViewById(R.id.Metode);
        norek = findViewById(R.id.norek);
        kirim2 = findViewById(R.id.kirimbarang);
        nama = findViewById(R.id.namaPemilik);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        date2 = findViewById(R.id.date);
        date2.setText(currentDate);
        btnback = findViewById(R.id.backToDaftarOrder);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(KonfirmasiOrder.this,DaftarOrder.class);
                startActivity(intent);
                finish();
            }
        });
        kirim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String skodeorder = koderorder.getText().toString();
                String snamapemilik = nama.getText().toString();
                String smetode = metode4.getText().toString();
                String sDate = date2.getText().toString();
                String SidAcount = idakunn.getText().toString();
                if (!skodeorder.equals("") && !snamapemilik.equals("") && !smetode.equals("") && !sDate.equals("") && !SidAcount.equals("")){

                    new AlertDialog.Builder(KonfirmasiOrder.this)
                            .setIcon(R.drawable.lcf)
                            .setTitle("Low Calory Food")
                            .setMessage("Apakah kamu yakin kirim data konfirmasi")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dataOrder();
                                }
                            })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                }else{
                    Toast.makeText(KonfirmasiOrder.this,"Ada yang kosong",Toast.LENGTH_SHORT).show();
                }

            }
        });
        getIncomingExtra();
        TotalHarga();


    }
    private void getIncomingExtra(){
        if(getIntent().hasExtra("orderid")){

            String orderid = getIntent().getStringExtra("orderid");

            setDataActivity(orderid);

        }
    }
    private void setDataActivity(String orderid){
        koderorder.setText(orderid);
    }
    public void TotalHarga(){
        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.GET, ALAMAT_DATA_NOREK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    try{
                        JSONObject jsonObject =  new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String metode2 = object.getString("metode").trim();
                                String norek2 = object.getString("norek");
                                metode4.setText(metode2);
                                norek.setText(norek2);
                            }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(sr);
    }
    void dataOrder() {
        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST, ALAMAT_DATA_KONFORDER, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String resp = jsonObject.getString("server_response");
                    if (resp.equals("[{\"status\":\"OK\"}]")) {
                        Toast.makeText(getApplicationContext(), "Data Order Telah masuk", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(KonfirmasiOrder.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KonfirmasiOrder.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", getId);
                params.put("idorder", koderorder.getText().toString());
                params.put("nama", nama.getText().toString());
                params.put("metode", metode4.getText().toString());

                return params;
            }
        };
        rq.add(sr);
    }
}