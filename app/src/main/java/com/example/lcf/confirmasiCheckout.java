package com.example.lcf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lcf.Activity.MainActivity;
import com.example.lcf.Cart.Cart;
import com.example.lcf.LoginRegister.DbContract;
import com.example.lcf.LoginRegister.VolleyConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class confirmasiCheckout extends AppCompatActivity {
    private String KEY_TOTAL = "TOTAL";
    private String KEY_ORDER = "ORDER";
    private  String total,orderid;
    TextView hargaTotal,orderidd;
    Button checkout;
    private String ALAMAT_DATA_KONFCHECKOUT="https://ws-tif.com/lcfp/AplikasiMobileAPI/checkout.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmasi_checkout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle extras = getIntent().getExtras();
        total = extras.getString(KEY_TOTAL);
        orderid = extras.getString(KEY_ORDER);
        hargaTotal = findViewById(R.id.idhargatotal);
        orderidd = findViewById(R.id.orderidd);
        checkout = findViewById(R.id.checkout2);
        orderidd.setText(orderid);
        hargaTotal.setText(total);

    checkout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new AlertDialog.Builder(confirmasiCheckout.this)
                    .setIcon(R.drawable.lcf)
                    .setTitle("Low Calory Food")
                    .setMessage("Apakah kamu yakin melakukan konfirmasi checkout")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String sOrderid = orderidd.getText().toString();
                            if(!sOrderid.equals("")){
                                CreateDataToServer(sOrderid);


                            }else{
                                Toast.makeText(confirmasiCheckout.this,"Data Ada Yang Kosong",Toast.LENGTH_SHORT).show();
                            }
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
    public void CreateDataToServer(final String orderidd) {
        if (checkNetworkConnection()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ALAMAT_DATA_KONFCHECKOUT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if (resp.equals("[{\"status\":\"OK\"}]")) {
                                    Toast.makeText(getApplicationContext(), "Barang berhasil Checkout", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(confirmasiCheckout.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(getApplicationContext(), "Barang gagal Checkout", Toast.LENGTH_SHORT).show();
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
                    params.put("orderidd", orderidd);
                    return params;
                }
            };

            VolleyConnection.getInstance(confirmasiCheckout.this).addToRequestQue(stringRequest);

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
}
