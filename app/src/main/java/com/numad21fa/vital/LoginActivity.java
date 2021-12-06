package com.numad21fa.vital;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    Button btn_exit = findViewById(R.id.btn_exit);
    Button btn_toRegisterPage = findViewById(R.id.btn_toRegPage);
    //btn_toRegisterPage.setOnClickListener();
    btn_exit.setOnClickListener(v -> finish());
  }
}
