package com.example.lcf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.bumptech.glide.Glide;
import com.example.lcf.Activity.MainActivity;
import com.example.lcf.Cart.Adapter.AdapterData2;
import com.example.lcf.Cart.Cart;
import com.example.lcf.Cart.Model.DataModel2;
import com.example.lcf.LoginRegister.DbContract;
import com.example.lcf.LoginRegister.VolleyConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class detail extends AppCompatActivity {

    ImageView imageViewmakanan,backtodashboard,cart,plusquantity, minusquantity;
    TextView idproduk,textViewnamamakanan, textviewinfomakanan,quantitynumber,harga,idAcount;

    Button addtoCart;


    int quantity;
    private String KEY_NAME = "Response2";
    SessionManager sessionManager;
    private String ALAMAT_DATA_CART="https://ws-tif.com/lcfp/AplikasiMobileAPI/dataCart.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailproduk);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        //kirim_data2();
        idAcount = findViewById(R.id.idAccount2);
        HashMap<String, String> user =  sessionManager.getUserDetail();
        String mNama = user.get(sessionManager.NAMALENGKAP);
        String mID = user.get(sessionManager.ID);
        idAcount.setText(mID);

        idproduk = findViewById(R.id.idproduk2);
        imageViewmakanan = findViewById(R.id.GambarProduk);
        textViewnamamakanan = findViewById(R.id.NamaProduk);
        textviewinfomakanan = findViewById(R.id.DeskripsiText);
        backtodashboard = findViewById(R.id.BackDashboard);
        quantitynumber = findViewById(R.id.qtyNumber);
        plusquantity = findViewById(R.id.plus);
        minusquantity  = findViewById(R.id.minus);
        harga = findViewById(R.id.HargaProduk);
        addtoCart = findViewById(R.id.AddToCart);
        cart = findViewById(R.id.CartDetail);
        getIncomingExtra();

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sIdproduk = idproduk.getText().toString();
                String sQuantity = quantitynumber.getText().toString();
                String sIDAccount = idAcount.getText().toString();
                if(!sIdproduk.equals("") && !sQuantity.equals("") && !sIDAccount.equals("")){
                    CreateDataToServer(sIdproduk, sQuantity, sIDAccount);

                }else{
                    Toast.makeText(detail.this,"Data Tidak Lengkap",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(detail.this, Cart.class);
                    startActivity(intent);
            }
        });

        backtodashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(detail.this, MainActivity.class));
            }
        });
        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // coffee price
                int basePrice = Integer.parseInt(getIntent().getStringExtra("harga_makanan"));
                quantity++;
                displayQuantity();
                int coffePrice = basePrice * quantity;
                String setnewPrice = String.valueOf(coffePrice);
                harga.setText(setnewPrice);


            }
        });
        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice = Integer.parseInt(getIntent().getStringExtra("harga_makanan"));
                // because we dont want the quantity go less than 0
                if (quantity == 0) {

                    Toast.makeText(detail.this, "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();

                } else {
                    quantity--;
                    displayQuantity();
                    int coffePrice = basePrice * quantity;
                    String setnewPrice = String.valueOf(coffePrice);
                    harga.setText(setnewPrice);

                }
            }
        });



    }
    private void getIncomingExtra(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("foto_makanan") && getIntent().hasExtra("Nama_Makanan") && getIntent().hasExtra("info_makanan") && getIntent().hasExtra("harga_makanan")){

            String id = getIntent().getStringExtra("id");
            String fotomakanan = getIntent().getStringExtra("foto_makanan");
            String namaMakanan = getIntent().getStringExtra("Nama_Makanan");
            String infomakanan = getIntent().getStringExtra("info_makanan");
            String hargamakanan = getIntent().getStringExtra("harga_makanan");

            setDataActivity(id,fotomakanan,namaMakanan,infomakanan,hargamakanan);

        }
    }
    private void setDataActivity(String id, String fotomakanan, String namaMakanan, String infomakanan,String hargamakanan){
        Glide.with(this).asBitmap().load("https://ws-tif.com/lcfp/food_ordering/"+fotomakanan).into(imageViewmakanan);
        idproduk.setText(id);
        textViewnamamakanan.setText(namaMakanan);
        textviewinfomakanan.setText(infomakanan);
        harga.setText(hargamakanan);

    }
    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }


    public void CreateDataToServer(final String Idproduk, final String quantitynumber, final String idAcount) {
        if (checkNetworkConnection()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_CART_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if (resp.equals("[{\"status\":\"OK\"}]")) {
                                    Toast.makeText(getApplicationContext(), "Barang berhasil masuk cart", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), "Barang gagal masuk cart", Toast.LENGTH_SHORT).show();
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
                    params.put("Idproduk", Idproduk);
                    params.put("quantitynumber", quantitynumber);
                    params.put("idAcount", idAcount);
                    return params;
                }
            };

            VolleyConnection.getInstance(detail.this).addToRequestQue(stringRequest);

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
