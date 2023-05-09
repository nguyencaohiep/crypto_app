package com.example.cryptoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cryptoapp.dao.Api;
import com.example.cryptoapp.dao.Crypto;
import com.example.cryptoapp.dao.ResponeDetail;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity  extends AppCompatActivity {
    private String userId;
    private String name;
    private int cryptoId;
    private ImageView back;
    private ImageView image;
    private TextView nameCrypto;
    private TextView symbol;
    private TextView chainname;
    private TextView address;
    private TextView type;
    private TextView totalSupply;
    private TextView marketCap;
    private TextView volume24h;
    private TextView des;
    DecimalFormat df = new DecimalFormat("#.##");

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        userId = i.getStringExtra("id");
        cryptoId = i.getIntExtra("cryptoId", 1);

        image = findViewById(R.id.image);
        nameCrypto = findViewById(R.id.nameCrypto);
        symbol = findViewById(R.id.symbol);
        back = findViewById(R.id.back);
        chainname = findViewById(R.id.chainname);
        address = findViewById(R.id.address);
        type = findViewById(R.id.type);
        marketCap = findViewById(R.id.marketCap);
        volume24h = findViewById(R.id.volume24h);
        des = findViewById(R.id.des);
        totalSupply = findViewById(R.id.totalSupply);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleback(userId, name);
            }
        });

        getDetailCrypto();
    }

    private void getDetailCrypto() {
       Api.api.getDetail(cryptoId).enqueue(new Callback<ResponeDetail>() {
           @Override
           public void onResponse(Call<ResponeDetail> call, Response<ResponeDetail> response) {
               ResponeDetail res = response.body();
               Crypto crypto = res.getData();
               Picasso.get().load(crypto.getImage()).into(image);
               nameCrypto.setText(crypto.getName());
               symbol.setText("Symbol : "+crypto.getSymbol());
               chainname.setText("ChainName : "+crypto.getChainname());
               address.setText("Address : "+crypto.getAddress());
               type.setText("Type : "+crypto.getType());
               totalSupply.setText("Total Supply : "+df.format(crypto.getTotalSupply()));
               marketCap.setText("MarketCap : "+df.format(crypto.getMarketCap()));
               volume24h.setText("Volume24h"+df.format(crypto.getVolume24h()));
               des.setText("Description : "+crypto.getDes());
           }

           @Override
           public void onFailure(Call<ResponeDetail> call, Throwable t) {

               Toast.makeText(DetailActivity.this, "Gặp lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void handleback(String userId, String name) {
        Intent i = new Intent(DetailActivity.this, MainActivity.class);
        i.putExtra("id", userId);
        i.putExtra("name", name);
        startActivity(i);
    }
}
