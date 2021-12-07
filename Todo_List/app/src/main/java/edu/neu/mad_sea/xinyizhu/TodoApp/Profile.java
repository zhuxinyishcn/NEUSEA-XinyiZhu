package edu.neu.mad_sea.xinyizhu.TodoApp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Profile extends AppCompatActivity {

  ImageView imageView;
  TextView name;
  TextView email;
  TextView userId;
  Button signOut;
  private GoogleSignInClient mGoogleSignInClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail().requestIdToken(getString(R.string.server_client_id))
        .build();
    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    signOut = findViewById(R.id.signOut);
    name = findViewById(R.id.name);
    email = findViewById(R.id.email);
    imageView = findViewById(R.id.avatars);
    userId = findViewById(R.id.userId);

    GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
    if (acct != null) {
      String personName = acct.getDisplayName();
      String personEmail = acct.getEmail();
      String personId = acct.getId();
      Uri personPhoto = acct.getPhotoUrl();
      name.setText(personName);
      email.setText(personEmail);
      userId.setText(personId);
      Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
    }
  }

  public void signOut(View view) {
    switch (view.getId()) {
      case R.id.signOut:
        signOut();
        break;
    }
  }

  private void signOut() {
    mGoogleSignInClient.signOut()
        .addOnCompleteListener(this, task -> {
          Toast toast = Toast
              .makeText(this, "Sign out completely done!:)", Toast.LENGTH_LONG);
          toast.show();
          finish();
        });
  }
}