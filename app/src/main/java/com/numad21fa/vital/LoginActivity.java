package com.numad21fa.vital;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
    btn_exit.setOnClickListener(v -> finish());
  }

  public void toRegPage() {
    Intent intent = new Intent(this, RegisterActivity.class);
    startActivity(intent);
  }

  public void login() {
    String email = userEmail.getText().toString().trim();
    String password = userPassword.getText().toString().trim();
    if (TextUtils.isEmpty(email)) {
      userEmail.setError("Email is required!");
      return;
    }
    if (TextUtils.isEmpty(password)) {
      userPassword.setError("Password is required!");
      return;
    }
    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
          Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
          startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else {
          Toast.makeText(LoginActivity.this, "ERROR: " + task.getException() , Toast.LENGTH_SHORT).show();

        }
      }
    });
  }

  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_toRegPage:
        toRegPage();
        finish();
        break;

      case R.id.btn_logIn:
        login();
        break;
    }
  }
}
