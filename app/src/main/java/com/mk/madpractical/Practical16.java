package com.mk.madpractical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Practical16 extends AppCompatActivity {
    TextView rollno, name, sem;
    Button action, view, delete;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical16);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Practical16_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rollno = findViewById(R.id.Pr16RollNo);
        name = findViewById(R.id.Pr16Name);
        sem = findViewById(R.id.Pr16Semester);
        action = findViewById(R.id.Pr16action_btn);
        delete = findViewById(R.id.Pr16delete_btn);
        view = findViewById(R.id.Pr16view_btn);

        dbHelper = new DBHelper(this);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbHelper.updateData(rollno.getText().toString(), name.getText().toString(), sem.getText().toString())) {
                    Toast.makeText(Practical16.this, "Data Updated Sucessfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Practical16.this, "There is problem Updating the data may be the RollNo is not found in the database", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Practical16.this, ViewData.class));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbHelper.deleteData(rollno.getText().toString())){
                    Toast.makeText(Practical16.this, "Data deleted sucessfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Practical16.this, "There is problem Deleting the data may be the RollNo is not found in the database", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }
}