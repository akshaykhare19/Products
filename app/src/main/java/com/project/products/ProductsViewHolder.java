package com.project.products;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ProductsViewHolder extends RecyclerView.ViewHolder {

    TextView itemTitle, itemPrice;
    ImageView itemImage;

    public ProductsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemTitle = (TextView)itemView.findViewById(R.id.item_title);
        this.itemPrice = (TextView)itemView.findViewById(R.id.item_price);
        this.itemImage = (ImageView)itemView.findViewById(R.id.item_image);
    }

}
