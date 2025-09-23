package com.mad.pr11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Practical11_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        start = findViewById(R.id.Practical11_start_btn);
        stop = findViewById(R.id.Practical11_stop_btn);
        Intent service = new Intent(this, MyService.class);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (v.equals(start)) {
                        startService(service);
                    } else {
                        stopService(service);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                 }
            }
        };
        start.setOnClickListener(listener);
        stop.setOnClickListener(listener);
    }
}