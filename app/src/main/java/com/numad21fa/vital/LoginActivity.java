package com.numad21fa.vital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
  EditText userEmail, userPassword;
  Button registerBtn;
  FirebaseAuth fAuth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    userEmail = findViewById(R.id.regiterEmail);
    userPassword = findViewById(R.id.registerPassward);
    registerBtn = findViewById(R.id.btn_register);
    fAuth = FirebaseAuth.getInstance();
    Button btn_exit = findViewById(R.id.btn_exit);
    Button btn_toRegisterPage = findViewById(R.id.btn_toRegPage);
    //btn_toRegisterPage.setOnClickListener();
    btn_exit.setOnClickListener(v -> finish());
  }

  public void toRegPage(View view) {
    Intent intent = new Intent(this, RegisterActivity.class);
    startActivity(intent);
  }

  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_toRegPage:
        toRegPage(view);
        break;
    }
  }
}
