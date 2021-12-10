package com.numad21fa.vital;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.numad21fa.vital.shaky.ShakyActivity;
import com.numad21fa.vital.webservice.FDCWebServiceActivity;

public class MainActivity extends AppCompatActivity {

    final String CURRENT_FRAGMENT_TAG = "CURRENT_FRAGMENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Bottom Navigation View.
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        Fragment curr_fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_FRAGMENT_TAG);
        switch (item.getItemId()) {
            case R.id.homeFragment:
                selectedFragment = new HomeFragment();
                break;
            case R.id.dashboardFragment:
                selectedFragment = new DashboardFragment();
                break;
            case R.id.profileFragment:
                selectedFragment = new ProfileFragment();
                break;
        }
        if (selectedFragment != null
            // TODO: to be completed
//        && curr_fragment != null && !curr_fragment.getClass().isInstance(selectedFragment)
        ) {
            Log.d("MainActivity", "---> line 40");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.navHostFragment, selectedFragment, CURRENT_FRAGMENT_TAG).commit();
        }
        Log.d("MainActivity", "---> out of if");
        return true;
    };

    public void openWebServiceView() {
        Intent intentWebService = new Intent(this, FDCWebServiceActivity.class);
        startActivity(intentWebService);
    }

    public void openShakyView() {
        Intent intentShaky = new Intent(this, ShakyActivity.class);
        startActivity(intentShaky);
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnWebService:
                openWebServiceView();
                break;
            case R.id.btnShaky:
                openShakyView();
                break;
        }

    }
}