package com.example.productrecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class ProductActivity extends AppCompatActivity {

    String title ;
    TextView product_title,product_description;
    ImageView product_image;
    Bundle bundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        product_title = findViewById(R.id.product_title);
        product_description = findViewById(R.id.product_description);
        product_image = findViewById(R.id.product_image);
        title = getIntent().getStringExtra("title");
        product_title.setText(title);
       bundle = getIntent().getBundleExtra("key");
       product_description.setText(bundle.getString("description"));


    }
}
