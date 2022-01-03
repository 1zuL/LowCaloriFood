package com.example.lcf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

public class confirmasiCheckout extends AppCompatActivity {
    private String KEY_TOTAL = "TOTAL";
    private String KEY_ORDER = "ORDER";
    private  String total,orderid;
    TextView hargaTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmasi_checkout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle extras = getIntent().getExtras();
        total = extras.getString(KEY_TOTAL);
        orderid = extras.getString(KEY_ORDER);
        hargaTotal = findViewById(R.id.idhargatotal);
        hargaTotal.setText(total);


    }
}