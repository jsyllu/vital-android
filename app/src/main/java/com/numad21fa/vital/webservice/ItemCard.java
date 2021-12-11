package com.numad21fa.vital.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.numad21fa.vital.R;

public class ItemCard extends AppCompatActivity {
    private final String description;

    public ItemCard(String description) {
        this.description = description;
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public String getDescription() {
        return description;
    }
}