package com.numad21fa.vital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.numad21fa.vital.webservice.FDCWebServiceActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openWebServiceView() {
        Intent intentWebService = new Intent(this, FDCWebServiceActivity.class);
        startActivity(intentWebService);
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnWebService:
                openWebServiceView();
        }

    }

}