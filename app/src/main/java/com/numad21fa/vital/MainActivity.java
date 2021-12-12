package com.numad21fa.vital;

import android.content.Intent;

import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.numad21fa.vital.models.FDCFood;
import com.numad21fa.vital.webservice.ItemCard;
import java.util.ArrayList;
import java.util.List;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements
    BottomNavigationView.OnNavigationItemSelectedListener, OnClickListener {

  private final String CURRENT_FRAGMENT_TAG = "CURRENT_FRAGMENT_TAG";
  private BottomNavigationView bottomNavigationView;
  private EditText edit_search_input;
  private List<FDCFood> search_result_foods;
  private ArrayList<ItemCard> search_result_item_list_view;
  private FirebaseUser user;
  private String uID;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //Initialize Bottom Navigation View.
    bottomNavigationView = findViewById(R.id.bottomNavigationView);
    bottomNavigationView.setOnNavigationItemSelectedListener(this);
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.navHostFragment, new HomeSearchFragment()).commit();
    // get current user
    user = FirebaseAuth.getInstance().getCurrentUser();
    if (user == null) {
      // not logged in
    } else {
      // logged in
      uID = user.getUid();

    }
    Toast.makeText(this, "UID: " + uID, Toast.LENGTH_SHORT).show();
    //UZ22EAAdVhWkxxC2OMIW1MuLXzj1
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