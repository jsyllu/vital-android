package com.numad21fa.vital;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.numad21fa.vital.models.FDCFood;
import com.numad21fa.vital.webservice.FDCWebServiceActivity;
import com.numad21fa.vital.webservice.ItemCard;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, OnClickListener {

    private final String CURRENT_FRAGMENT_TAG = "CURRENT_FRAGMENT_TAG";
    private BottomNavigationView bottomNavigationView;
    private EditText edit_search_input;
    private List<FDCFood> search_result_foods;
    private ArrayList<ItemCard> search_result_item_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize Bottom Navigation View.
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.navHostFragment, new HomeSearchFragment()).commit();
//                bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

//    @SuppressLint("NonConstantResourceId")
//    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
//        Fragment selectedFragment = null;
//        Fragment curr_fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_FRAGMENT_TAG);
//        switch (item.getItemId()) {
//            case R.id.searchFragment:
//                selectedFragment = new HomeSearchFragment();
//                break;
//            case R.id.dashboardFragment:
//                selectedFragment = new DashboardFragment();
//                break;
//            case R.id.profileFragment:
//                selectedFragment = new ProfileFragment();
//                break;
//        }
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.navHostFragment, selectedFragment, CURRENT_FRAGMENT_TAG).commit();
//        return true;
//    };


    public void openShakyView() {
        Intent intentShaky = new Intent(this, ShakyActivity.class);
        startActivity(intentShaky);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_shaky:
                openShakyView();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        Fragment curr_fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_FRAGMENT_TAG);
        switch (item.getItemId()) {
            case R.id.searchFragment:
                selectedFragment = new HomeSearchFragment();
                break;
            case R.id.dashboardFragment:
                selectedFragment = new DashboardFragment();
                break;
            case R.id.profileFragment:
                selectedFragment = new ProfileFragment();
                break;
            default:
                return false;
        }
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.navHostFragment, selectedFragment, CURRENT_FRAGMENT_TAG).commit();
        return true;
    }
}