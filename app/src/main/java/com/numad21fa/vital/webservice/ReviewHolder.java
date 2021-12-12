package com.numad21fa.vital.webservice;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.numad21fa.vital.R;

public class ReviewHolder extends RecyclerView.ViewHolder {
    public TextView item_card_description_txt;
    public FloatingActionButton item_card_add_btn;

    // fetch the related ItemCard instance from the view
    public ReviewHolder(View itemView, final ItemCard.ItemClickListener listener) {
        super(itemView);
        item_card_description_txt = itemView.findViewById(R.id.item_card_description_txt);
        item_card_add_btn = itemView.findViewById(R.id.item_card_add_btn);

        // Set OnClick Listener active
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }
}