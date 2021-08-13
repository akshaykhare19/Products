package com.project.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductItemClicked {

    RecyclerView mRecyclerView;
    ProductsAdapter mAdapter;
    private static final String TAG = "Check";
    String url = "https://fakestoreapi.com/products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.product_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        fetchData(url);
        mAdapter = new ProductsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void fetchData(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        ArrayList<Products> productsArray = new ArrayList<>();
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject productsJsonObject = response.getJSONObject(i);
                                Products products = new Products();
                                products.setTitle(productsJsonObject.getString("title"));
                                products.setPrice(productsJsonObject.getDouble("price"));
                                products.setDescription(productsJsonObject.getString("description"));
                                products.setCategory(productsJsonObject.getString("category"));
                                products.setImage(productsJsonObject.getString("image"));

                                productsArray.add(products);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.updateProducts(productsArray);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                });

        queue.add(jsonArrayRequest);

    }

    @Override
    public void onItemClicked(Products product) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("title", product.title);
        intent.putExtra("description", product.description);
        intent.putExtra("price", product.price);
        intent.putExtra("category", product.category);
        intent.putExtra("imageUrl", product.image);

        startActivity(intent);

    }
}