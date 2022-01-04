package com.example.lcf.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.lcf.API.APIRequestData;
import com.example.lcf.API.RetroServer;
import com.example.lcf.Adapter.AdapterData;
import com.example.lcf.Model.DataModel;
import com.example.lcf.Model.ResponseModel;
import com.example.lcf.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuMakan extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_makan);

        rvData = findViewById(R.id.rv_data);
        lmData =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        retrieveData();

    }

    private void retrieveData() {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardRetrieveData();

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                /*"Kode : "+kode+" | Pesan :"+pesan*/
                /*"Kode : "+kode+" | Pesan :"+pesan*/
                //Toast.makeText(MainActivity.this,"Kode : "+kode+" | Pesan :"+pesan, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();

                adData = new AdapterData(MenuMakan.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }
        });
    }
}