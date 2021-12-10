package com.numad21fa.vital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.numad21fa.vital.models.User;

public class RegisterActivity extends AppCompatActivity {
    EditText userEmail, userPassword;
    Button registerBtn;
    Button toLoginBtn;
    String uID;
    FirebaseAuth fAuth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference db_users;
    //final String USER_TABLE = User.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // text
        userEmail = findViewById(R.id.registerEmail);
        userPassword = findViewById(R.id.registerPassword);
        // button
        registerBtn = findViewById(R.id.btn_register);
        toLoginBtn = findViewById(R.id.btn_toLoginPage);
        // firebase
        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();
        uID = user.getUid();
        database = FirebaseDatabase.getInstance();
        //db_users = database.getReference(USER_TABLE);


        // if user already logged in, return to main activity
//        if (fAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    userEmail.setError("Email cannot be empty!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    userPassword.setError("Password cannot be empty!");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            uID = user.getUid();
                            storeUser(email, password, uID);
                            Toast.makeText(RegisterActivity.this, "Registration Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "ERROR: " + task.getException() , Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        toLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finishActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });


    }

    private void storeUser(String email, String pwd, String uid) {
        User newUser = new User(email, pwd);
        db_users.child(uid).setValue(newUser);

    }
}