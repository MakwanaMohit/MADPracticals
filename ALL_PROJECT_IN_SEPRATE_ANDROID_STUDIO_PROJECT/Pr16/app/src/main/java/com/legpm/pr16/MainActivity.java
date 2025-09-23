package com.legpm.pr16;

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

public class MainActivity extends AppCompatActivity {
    TextView rollno, name, sem;
    Button action, view, delete;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rollno = findViewById(R.id.main_RollNo);
        name = findViewById(R.id.main_Name);
        sem = findViewById(R.id.main_Semester);
        action = findViewById(R.id.main_action_btn);
        delete = findViewById(R.id.main_delete_btn);
        view = findViewById(R.id.main_view_btn);

        dbHelper = new DBHelper(this);
        dbHelper.insertData("1234","Student1","5");
        dbHelper.insertData("1235","Student2","5");
        dbHelper.insertData("1236","Student3","5");
        dbHelper.insertData("1237","Student4","5");
        dbHelper.insertData("1238","Student5","5");
        dbHelper.insertData("1239","Student6","5");
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbHelper.updateData(rollno.getText().toString(), name.getText().toString(), sem.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Data Updated Sucessfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "There is problem Updating the data may be the RollNo is not found in the database", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewData.class));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbHelper.deleteData(rollno.getText().toString())){
                    Toast.makeText(MainActivity.this, "Data deleted sucessfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "There is problem Deleting the data may be the RollNo is not found in the database", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}