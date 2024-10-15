package com.mk.madpractical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Practical15 extends AppCompatActivity {
    TextView rollno,name,sem;
    Button action,view;
    ListView listView;
    DBHelper dbHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical15);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Practical15_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rollno = findViewById(R.id.Pr15RollNo);
        name = findViewById(R.id.Pr15Name);
        sem = findViewById(R.id.Pr15Semester);
        action = findViewById(R.id.Pr15action_btn);
        view = findViewById(R.id.Pr15view_btn);

        dbHelper = new DBHelper(this);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.insertData(rollno.getText().toString(),name.getText().toString(),sem.getText().toString());
                Toast.makeText(Practical15.this, "Data Inserted Sucessfully", Toast.LENGTH_SHORT).show();
                rollno.setText("");
                name.setText("");
                sem.setText("");
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Practical15.this,ViewData.class));
            }
        });
    }

}