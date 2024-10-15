package com.mk.madpractical;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Practical12 extends AppCompatActivity {
    Practical12_brodcast brodcast;
    IntentFilter intentFilter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical12);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Practical12_ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.Practical12_textview);

        brodcast = new Practical12_brodcast();
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        registerReceiver(brodcast, intentFilter, Context.RECEIVER_NOT_EXPORTED);




    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(brodcast);
        super.onDestroy();
    }


    class Practical12_brodcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action != null) {
                switch (action) {
                    case "android.intent.action.ACTION_POWER_CONNECTED":
                        Toast.makeText(context, "Power connected to your device", Toast.LENGTH_SHORT).show();
                        textView.setText("power connected");
                        break;
                    case "android.intent.action.ACTION_POWER_DISCONNECTED":
                        Toast.makeText(context, "Power disconnected from your device", Toast.LENGTH_SHORT).show();
                        textView.setText("power disconnected");
                        break;
                    default:
                        Toast.makeText(context, "Unknown action: " + action, Toast.LENGTH_SHORT).show();
                        textView.setText("unknown action triggered");
                        break;
                }
            }
        }
    }
}