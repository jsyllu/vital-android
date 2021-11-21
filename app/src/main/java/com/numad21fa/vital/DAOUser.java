package com.numad21fa.vital;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.numad21fa.vital.models.User;

public class DAOUser {
  final String USER_TABLE = User.class.getSimpleName();
  private final DatabaseReference db_users;
  private String email;

  public DAOUser() {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    db_users = db.getReference(USER_TABLE);
  }

  public DAOUser(String email) {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    db_users = db.getReference(USER_TABLE);
    this.email = email;
  }

  public void register (String email, String password) {
    // TODO: to be updated
  }

  public void login (String email, String password) {
    // TODO: to be updated
  }

}
