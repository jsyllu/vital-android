package com.numad21fa.vital.webservice;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.numad21fa.vital.R;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewHolder> {

    private ArrayList<ItemCard> itemList;
    private ItemCard.ItemClickListener listener;

    public ReviewAdapter(ArrayList<ItemCard> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(ItemCard.ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_card, parent, false);
        return new ReviewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        ItemCard currentItem = itemList.get(position);

        holder.item_card_description_txt.setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}