package com.example.cryptoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cryptoapp.dao.Api;
import com.example.cryptoapp.dao.ResponeAssets;
import com.example.cryptoapp.dao.asset.Asset;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetActivity  extends AppCompatActivity {
    private TextView totalAssets;
    private String userId;
    private String name;
    private String address;
    private LinearLayout assetDetail;
    private ImageView back;
    DecimalFormat df = new DecimalFormat("#.##");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        userId = i.getStringExtra("id");
        address = i.getStringExtra("address");
        totalAssets = findViewById(R.id.assets);
        assetDetail = findViewById(R.id.assets_detail);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleback(userId, name);
            }
        });

        callAPIGetAssets();
    }

    private void callAPIGetAssets() {
        Api.api.getAssets(address).enqueue(new Callback<ResponeAssets>() {
            @Override
            public void onResponse(Call<ResponeAssets> call, Response<ResponeAssets> response) {
                ResponeAssets res = response.body();
                totalAssets.setText("$" + String.valueOf(df.format(res.getData().getTotal())));

                for(Asset asset: res.getData().getAssets()) {
                    LinearLayout ll = new LinearLayout(AssetActivity.this);
                    // set width and height
                    LinearLayout.LayoutParams llLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
                    llLayoutParams.setMargins(0,0,0,5);
                    ll.setLayoutParams(llLayoutParams);
                    ll.setOrientation(LinearLayout.HORIZONTAL);
                    ll.setGravity(Gravity.CENTER_VERTICAL);
                    ll.setBackgroundResource(R.drawable.crypto);

                    ImageView imageView = new ImageView(AssetActivity.this);
                    Picasso.get().load(asset.getImage()).into(imageView);
                    LinearLayout.LayoutParams imgLayoutParams = new LinearLayout.LayoutParams(100, 100);
                    imageView.setLayoutParams(imgLayoutParams);
                    imageView.setPadding(5, 0,0,0);
                    ll.addView(imageView);

                    TextView nameView = new TextView(AssetActivity.this);
                    nameView.setLayoutParams(new
                            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    nameView.setText(asset.getTokenName());
                    nameView.setTextColor(getResources().getColor(R.color.white));
                    nameView.setPadding(15,0,0,0);
                    ll.addView(nameView);


                    TextView priceView = new TextView(AssetActivity.this);
                    LinearLayout.LayoutParams priceLayoutParams = new
                            LinearLayout.LayoutParams(0,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                    priceView.setText(String.valueOf(df.format(asset.getAmount()* (float) Math.pow(10,-18))));
                    priceView.setTextSize(25);
                    priceView.setTextColor(getResources().getColor(R.color.white));
                    priceView.setPadding(10,10,10,10);
                    priceView.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                    ll.addView(priceView);

                    assetDetail.addView(ll);
                }
            }

            @Override
            public void onFailure(Call<ResponeAssets> call, Throwable t) {
                Toast.makeText(AssetActivity.this, "Gặp lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void handleback(String userId, String name) {
        Intent i = new Intent(AssetActivity.this, MainActivity.class);
        i.putExtra("id", userId);
        i.putExtra("name", name);
        startActivity(i);
    }
}
