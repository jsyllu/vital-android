package com.numad21fa.vital;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass. Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements OnClickListener {
  TextView username;
  TextView email;
  String usernameString;
  String emailString;
  String UID;
  FirebaseUser user;
  FirebaseDatabase database;
  DatabaseReference db_users;
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public ProfileFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment ProfileFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ProfileFragment newInstance(String param1, String param2) {
    ProfileFragment fragment = new ProfileFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_profile, container, false);;
    Button btn_login = view.findViewById(R.id.btn_login);
    Button btn_logout = view.findViewById(R.id.btn_logout);
    btn_login.setOnClickListener(this);
    btn_logout.setOnClickListener(this);
    // textview
    username = view.findViewById(R.id.loggedUsername);
    email = view.findViewById(R.id.loggedEmail);
    // firebase get
    user = FirebaseAuth.getInstance().getCurrentUser();
    if (user != null) {
      UID = user.getUid();
      btn_login.setVisibility(View.INVISIBLE);
      btn_logout.setVisibility(View.VISIBLE);
    } else {
      btn_login.setVisibility(View.VISIBLE);
      btn_logout.setVisibility(View.INVISIBLE);
    }
    database = FirebaseDatabase.getInstance();
    db_users = database.getReference("User");
    if (UID != null) {
      db_users.child(UID).child("username").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DataSnapshot> task) {
          if (task.isSuccessful()) {
            usernameString = String.valueOf(task.getResult().getValue());
            username.setText(usernameString);
            username.setVisibility(View.VISIBLE);
            view.findViewById(R.id.textView).setVisibility(View.VISIBLE);
          } else {
            Log.e("firebase", "Error getting data", task.getException());
          }
        }
      });
      db_users.child(UID).child("email").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DataSnapshot> task) {
          if (task.isSuccessful()) {
            emailString = String.valueOf(task.getResult().getValue());
            email.setText(emailString);
            email.setVisibility(view.VISIBLE);
            view.findViewById(R.id.textView2).setVisibility(View.VISIBLE);

          } else {
            Log.e("firebase", "Error getting data", task.getException());
          }
        }
      });

    }


    return view;
  }

  public void login(View view) {
    // TODO: complete authentication logic
    // authentication logic is implemented in LoginActivity
    // open login screen
    Intent intent = new Intent(getActivity(), LoginActivity.class);
    startActivity(intent);
  }

  public void logout(View view) {
    FirebaseAuth.getInstance().signOut();
    Toast.makeText(getContext(), "Successfully logged out", Toast.LENGTH_SHORT).show();

  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_login:
        login(this.getView());
        break;

      case R.id.btn_logout:
        logout(this.getView());
        break;
    }
  }
}