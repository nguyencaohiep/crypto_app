package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cryptoapp.dao.Api;
import com.example.cryptoapp.dao.Crypto;
import com.example.cryptoapp.dao.ResponeData;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView nameView;
    private LinearLayout portfolio;
    private String name;
    private String userId;

    private Button findBtn;
    private EditText addressEdit;

    DecimalFormat df = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        userId = i.getStringExtra("id");

        nameView = findViewById(R.id.name);
        nameView.setText("Hello, " + name.split("@")[0]);
        addressEdit = findViewById(R.id.address);

        findBtn = findViewById(R.id.find_asset);

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findAsset(name, userId);
            }
        });

        portfolio = (LinearLayout) findViewById(R.id.portfolio);

        callAPI();
    }

    private void callAPI() {
        Api.api.getCryptos().enqueue(new Callback<ResponeData>() {
            @Override
            public void onResponse(Call<ResponeData> call, Response<ResponeData> response) {
                ResponeData res = response.body();
                for(Crypto crypto: res.getData()) {
                    LinearLayout ll = new LinearLayout(MainActivity.this);
                    // set width and height
                    LinearLayout.LayoutParams llLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
                    llLayoutParams.setMargins(0,0,0,5);
                    ll.setLayoutParams(llLayoutParams);
                    ll.setOrientation(LinearLayout.HORIZONTAL);
                    ll.setGravity(Gravity.CENTER_VERTICAL);
                    ll.setBackgroundResource(R.drawable.crypto);
                    ll.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            handle(crypto.getId(), name, userId);
                        }
                    });

                    ImageView imageView = new ImageView(MainActivity.this);
                    Picasso.get().load(crypto.getImage()).into(imageView);
                    LinearLayout.LayoutParams imgLayoutParams = new LinearLayout.LayoutParams(100, 100);
                    imageView.setLayoutParams(imgLayoutParams);
                    imageView.setPadding(5, 0,0,0);
                    ll.addView(imageView);

                    TextView nameView = new TextView(MainActivity.this);
                    nameView.setLayoutParams(new
                            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    nameView.setText(crypto.getName());
                    nameView.setTextColor(getResources().getColor(R.color.white));
                    nameView.setPadding(15,0,0,0);
                    ll.addView(nameView);

                    TextView priceView = new TextView(MainActivity.this);
                    LinearLayout.LayoutParams priceLayoutParams = new
                            LinearLayout.LayoutParams(0,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                    priceView.setText("$"+String.valueOf(df.format(crypto.getPriceUSD())));
                    priceView.setTextSize(25);
                    priceView.setTextColor(getResources().getColor(R.color.white));
                    priceView.setPadding(10,10,10,10);
                    priceView.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                    ll.addView(priceView);

                    portfolio.addView(ll);
                }
            }

            @Override
            public void onFailure(Call<ResponeData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gặp lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void handle(int id, String name, String userId) {
        Intent i = new Intent(MainActivity.this, DetailActivity.class);
        i.putExtra("id", userId);
        i.putExtra("name", name);
        i.putExtra("cryptoId", id);
        startActivity(i);
    }

    private void findAsset(String name, String userId) {
        Intent i = new Intent(MainActivity.this, AssetActivity.class);
        String address = addressEdit.getText().toString();
        i.putExtra("id", userId);
        i.putExtra("name", name);
        i.putExtra("address", address);
        startActivity(i);
    }
}