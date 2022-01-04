package com.example.lcf.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lcf.API.APIRequestData;
import com.example.lcf.API.RetroServer;
import com.example.lcf.Adapter.AdapterData;
import com.example.lcf.Cart.Cart;
import com.example.lcf.Model.DataModel;
import com.example.lcf.Model.ResponseModel;
import com.example.lcf.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuMakan extends AppCompatActivity {
    private RecyclerView rvData;
    AdapterData adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();
    SearchView searchView;
    ImageView gotoCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_makan);

        gotoCart = findViewById(R.id.goToCart);

        gotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuMakan.this, Cart.class);
                startActivity(intent);
            }
        });

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        rvData = findViewById(R.id.rv_data3);
        lmData =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        retrieveData("");//without keywordberjalan

    }

    private void filter(String newText) {
        List<DataModel> filteredList = new ArrayList<>();
        for (DataModel item : listData){
            if(item.getNamaproduk().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);
            }
        }
        adData.FilteredList(filteredList);
    }

    private void retrieveData(String search) {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.search(search);

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
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MenuMakan.this, "Gagal Menhubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }


}