package com.mk.madpractical;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Practical24 extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient signInClient;
    private GoogleSignInOptions gso;
    private ImageButton btn;
    private TextView username,txt;
    FirebaseUser currentUser;

    // Register the launcher for Activity Result API
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    // Handle the Google Sign-In result
                    Intent data = result.getData();
                    handleSignInResult(data);
                } else {
                    Toast.makeText(Practical24.this, "Sign-in failed", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical24);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Practical24layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase Auth and Google Sign-In client
        firebaseAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        signInClient = GoogleSignIn.getClient(this, gso);

        // Set up the login/logout button
        btn = findViewById(R.id.Practical24login);
        username = findViewById(R.id.Practical24username);
        txt = findViewById(R.id.Practical24txt);

        currentUser = firebaseAuth.getCurrentUser();

        // Check if user is logged in
        if (currentUser != null) {
            username.setText(currentUser.getDisplayName());
            txt.setText("Logout by clicking this button again");
            Toast.makeText(Practical24.this, "Logged in as: " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();

        }

        btn.setOnClickListener(v -> {
            currentUser = firebaseAuth.getCurrentUser();

            // Check if user is logged in
            if (currentUser != null) {
                // User is logged in, perform logout
                firebaseAuth.signOut();
                signInClient.signOut().addOnCompleteListener(task -> {
                    Toast.makeText(Practical24.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                });
                username.setText("Username");
                txt.setText("Login Using");
            } else {
                // User is not logged in, perform Google sign-in
                Intent signInIntent = signInClient.getSignInIntent();
                signInLauncher.launch(signInIntent);  // Use the ActivityResultLauncher to start sign-in
            }
        });
    }

    // Handle sign-in result
    private void handleSignInResult(@Nullable Intent data) {
        try {
            GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
            if (account != null) {
                // Firebase authentication with Google
                firebaseAuthWithGoogle(account.getIdToken());
            }
        } catch (ApiException e) {
            Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        firebaseAuth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign-in success, show a toast with the user's email (username)
                        currentUser = firebaseAuth.getCurrentUser();
                        if (currentUser != null) {
                            Toast.makeText(Practical24.this, "Logged in as: " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
                            username.setText(currentUser.getDisplayName());
                            txt.setText("Logout by clicking this button again");
                        }
                    } else {
                        Toast.makeText(Practical24.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
