package com.le.mad_practical_8;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {
    DrawerLayout d;
    ActionBarDrawerToggle a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         d = findViewById(R.id.menu_dr);
         a = new ActionBarDrawerToggle(this,d,R.string.op,R.string.cl);
         d.addDrawerListener(a);
         a.syncState();
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(a.onOptionsItemSelected(item)){
            return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }
}