package com.example.lcf.Activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.lcf.API.APIRequestData;
import com.example.lcf.API.RetroServer;
import com.example.lcf.Adapter.AdapterData;
import com.example.lcf.Cart.Cart;
import com.example.lcf.Model.DataModel;
import com.example.lcf.Model.ResponseModel;
import com.example.lcf.R;
import com.example.lcf.SessionManager;
import com.example.lcf.yogiapp.yogi;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    TextView txtHello, menu, fitnes;
    private String KEY_NAME = "NAMA";
    SessionManager sessionManager;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView gotoCart;
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);



        ImageSlider imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://www.labmeeting.com/wp-content/uploads/2020/08/calories-600.jpg"));
        slideModels.add(new SlideModel("https://legionathletics.com/wp-content/uploads/2016/06/healthy-meal.jpg"));
        slideModels.add(new SlideModel("https://ws-tif.com/lcfp/food_ordering/produk/16ITZ67hFVr6Q.jpeg"));
        slideModels.add(new SlideModel("https://ws-tif.com/lcfp/food_ordering/produk/16uutR4VGfuF2.jpeg"));
        slideModels.add(new SlideModel("https://post.greatist.com/wp-content/uploads/2020/09/1931-Low_Calorie_Diets-766x415-thumbnail-732x415.jpg"));

        imageSlider.setImageList(slideModels, true);

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MenuMakan.class));
            }
        });

        fitnes = findViewById(R.id.fitnes);
        fitnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, yogi.class));
            }
        });



        txtHello = (TextView) findViewById(R.id.NameAccount);
        //TextView logout = (TextView) findViewById(R.id.logut);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        gotoCart = findViewById(R.id.goToCart);

        gotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cart.class);
                startActivity(intent);
            }
        });


        sessionManager = new SessionManager(this);

        HashMap<String, String> user =  sessionManager.getUserDetail();
        String mNama = user.get(sessionManager.NAMALENGKAP);
        String mID = user.get(sessionManager.ID);
        txtHello.setText(mNama);
        //ImageSlider imageSlider = findViewById(R.id.slider);
        //List<SlideModel> slideModels = new ArrayList<>();
        //slideModels.add(new SlideModel("https://ws-tif.com/lcfp/food_ordering/produk/15Ak7lFMfvuJc.jpg"));
        //imageSlider.setImageList(slideModels, true);
        sessionManager.checkLogin();
        /*logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });*/

        //swipeRefreshLayout = findViewById(R.id.swipe_Refresh);
        rvData = findViewById(R.id.rv_data);
        lmData =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        retrieveData();

        /*swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    */


        drawerLayout =  findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if(mID.equals("")) {
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_logout).setVisible(false);

        }else{
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_login).setVisible(false);
        }

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            //super.onBackPressed();
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.lcf)
                    .setTitle("Low Calory Food")
                    .setMessage("Apakah kamu yakin")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
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

    }

    public void retrieveData(){
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

                adData =  new AdapterData(MainActivity.this,listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menhubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_logout:
                sessionManager.logout();
                break;
            case  R.id.nav_makanan:
                Intent intent =  new Intent(MainActivity.this,MenuMakan.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}