package com.project.products;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView productTitle, productDescription, productCategory, productPrice;
    ImageView productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        productTitle = findViewById(R.id.product_title);
        productDescription = findViewById(R.id.product_desc);
        productCategory = findViewById(R.id.product_category);
        productPrice = findViewById(R.id.product_price);
        productImage = findViewById(R.id.product_image);


        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("description");
        String category = getIntent().getStringExtra("category");
        double price = getIntent().getDoubleExtra("price", 0);
        String imageUrl = getIntent().getStringExtra("imageUrl");

        productTitle.setText(title);
        productDescription.setText(desc);
        productCategory.setText(getString(R.string.category, category));
        productPrice.setText(getString(R.string.price, price));
        Glide.with(this).load(imageUrl).into(productImage);

    }
}