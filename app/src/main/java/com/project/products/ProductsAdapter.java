package com.project.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsViewHolder> {

    ProductItemClicked listen;
    private ArrayList<Products> items = new ArrayList<>();
    public ProductsAdapter(ProductItemClicked listener) {
        this.listen = listener;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        final ProductsViewHolder viewHolder = new ProductsViewHolder(itemView);
        itemView.setOnClickListener(v ->
                listen.onItemClicked(items.get(viewHolder.getAdapterPosition())));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        Products item = items.get(position);
        holder.itemTitle.setText(item.title);
        holder.itemPrice.setText("" + item.price);
        Glide.with(holder.itemView.getContext()).load(item.image).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public void updateProducts(ArrayList<Products> updatedProducts)
    {
        items.clear();
        items.addAll(updatedProducts);
        notifyDataSetChanged();
    }
}
