package com.mk.madpractical;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView l;
    String[] Practicals = {
            "About: Author | Source code",
            "Microproject: Adhar Data",
            "Practical-01: Setup Android Studio & Hello World",
            "Practical-02: Activity Lifecycle Demo App",
            "Practical-03: Basic Calculator App",
            "Practical-04: LinearLayout UI Arrangement",
            "Practical-05: RelativeLayout UI Arrangement",
            "Practical-06: ScrollView for Long Lists",
            "Practical-07: ListView with Custom Adapter",
            "Practical-08: Navigation Drawer Implementation",
            "Practical-09: Bottom Navigation Tabs",
            "Practical-10: Intent for Data Passing",
            "Practical-11: Background Tasks with Services",
            "Practical-12: Broadcast Receivers Usage",
            "Practical-13: Data Sharing with Content Providers",
            "Practical-14: Access System Data with Content Providers",
            "Practical-15: SQLite DB: Insert & Read Data",
            "Practical-16: SQLite DB: Update & Delete Data",
            "Practical-17: Firebase Integration & Data Storage",
            "Practical-18: Firebase Data Display in RecyclerView",
            "Practical-19: MySQL Connection & Data Insertion",
            "Practical-20: MySQL Data Insertion via PHP",
            "Practical-21: Read MySQL Data via PHP & JSON",
            "Practical-22: Google Maps API: Display Location",
            "Practical-23: Google Maps API: Distance Calculation",
            "Practical-24: Google Account Login Integration"
    };

    private final Class[] Practical_activities = {
            about.class,
            MicroProject.class,
            Practical1.class,
            Practical2.class,
            Practical3.class,
            Practical4.class,
            Practical5.class,
            Practical6.class,
            Practical7.class,
            Practical8.class,
            Practical9.class,
            Practical10.class,
            Practical11.class,
            Practical12.class,
            Practical13.class,
            Practical14.class,
            Practical15.class,
            Practical16.class,
            Practical17.class,
            Practical18.class,
            Practical19.class,
            Practical19.class,
            Practical21.class,
            Practical22.class,
            Practical23 .class,
            Practical24.class,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        l = findViewById(R.id.practicallist);
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Practicals);

        l.setAdapter(arr);


        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 11) {
                    showInputDialog();
                } else if (i < Practical_activities.length) {
                    Intent intent = new Intent(MainActivity.this, Practical_activities[i]);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Practical: " + (i - 1) + " is comming soon", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void showInputDialog() {
        // Create EditText programmatically
        final EditText editTextInput = new EditText(this);
        editTextInput.setHint("Enter your text");
        editTextInput.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        new AlertDialog.Builder(this)
                .setTitle("Enter your text")
                .setView(editTextInput)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputText = editTextInput.getText().toString();
                        Intent pr10 = new Intent(MainActivity.this, Practical10.class);
                        pr10.putExtra("MSG", inputText);
                        startActivity(pr10);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
