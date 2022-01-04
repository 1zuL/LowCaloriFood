package com.example.lcf.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

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
    AdapterData adData;
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
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MenuMakan.this, "Gagal Menhubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                newText = newText.toLowerCase();
                ArrayList<DataModel> itemFilter = new ArrayList<>();
                for (DataModel model : listData){
                    String nama = model.getNamaproduk().toLowerCase();
                    if (nama.contains(newText)) {
                        itemFilter.add(model);
                    }
                }
            adData.setFilter(itemFilter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}