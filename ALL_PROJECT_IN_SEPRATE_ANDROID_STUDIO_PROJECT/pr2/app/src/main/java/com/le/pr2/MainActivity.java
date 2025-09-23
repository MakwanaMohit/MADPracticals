package com.le.pr2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         Log.d("Demo", "onCreate called..");
        Toast.makeText(getApplicationContext(), "mbgghjg", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Demo","onStart called..");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Demo","onResume called..");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Demo","onPause called..");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Demo","onStop called..");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Demo","onRestart called..");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("D","onDestroy called..");
    }
}