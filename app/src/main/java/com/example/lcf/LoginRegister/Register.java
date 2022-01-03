package com.example.lcf.LoginRegister;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lcf.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText name, email, passsword, confPassword,mobile,TTL;
    Button login, register;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        name = (EditText) findViewById(R.id.edt_usernameRegister);
        email = (EditText) findViewById(R.id.edt_emailRegister);
        passsword = (EditText) findViewById(R.id.edt_passwordRegister);
        confPassword = (EditText) findViewById(R.id.edt_confPasswordRegister);
        TTL = (EditText) findViewById(R.id.alamat);
        login = (Button) findViewById(R.id.btn_loginRegister);
        register = (Button) findViewById(R.id.btn_registerRegister);
        mobile =  (EditText) findViewById(R.id.mobile);
        progressDialog = new ProgressDialog(Register.this);

        login.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Register.this, Login.class);
                startActivity(loginIntent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sName = name.getText().toString();
                String sEmail = email.getText().toString();
                String sMobile = mobile.getText().toString();
                String sPassword = passsword.getText().toString();
                String sConfPassword = confPassword.getText().toString();
                String sTTL = TTL.getText().toString();

                if (sPassword.equals(sConfPassword) && !sPassword.equals("")) {
                    CreateDataToServer(sName, sEmail, sPassword, sMobile, sTTL);
                    Intent loginIntent = new Intent(Register.this, Login.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Gagal! Pasword tidak cocok!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void CreateDataToServer(final String name, final String email, final String password ,final String mobile, final String TTL) {
        if (checkNetworkConnection()) {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if (resp.equals("[{\"status\":\"OK\"}]")) {
                                    Toast.makeText(getApplicationContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
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
                    params.put("name", name);
                    params.put("email", email);
                    params.put("mobile", mobile);
                    params.put("password", password);
                    params.put("alamat", TTL);
                    return params;
                }
            };

            VolleyConnection.getInstance(Register.this).addToRequestQue(stringRequest);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
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