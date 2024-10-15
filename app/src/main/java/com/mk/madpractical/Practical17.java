package com.mk.madpractical;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Practical17 extends AppCompatActivity {
    TextView rollno, name, sem;
    Button action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical17);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Practical17_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rollno = findViewById(R.id.Pr17RollNo);
        name = findViewById(R.id.Pr17Name);
        sem = findViewById(R.id.Pr17Semester);
        action = findViewById(R.id.Pr17action_btn);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameval = name.getText().toString();
                String rollnoval = rollno.getText().toString();
                String semval = sem.getText().toString();

                if (rollnoval.isEmpty()) {
                    rollno.setError("RollNo Cannot be empty");
                    return;
                }
                if (nameval.isEmpty()) {
                    name.setError("Name Cannot be empty");
                    return;
                }
                if (semval.isEmpty()) {
                    sem.setError("Semester Cannot be empty");
                    return;
                }
                addData(rollnoval,nameval,semval);
            }
        });
    }

    private void addData(String rollnoval,String nameval,String semval) {
        HashMap<String , String> values = new HashMap<>();
        values.put("rollno",rollnoval);
        values.put("name",nameval);
        values.put("sem",semval);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference studentsRef = database.getReference("Students");
        String key = studentsRef.push().getKey();
        values.put("key",key);

        studentsRef.child(key).setValue(values).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Practical17.this, "Data Uploaded Sucessfully", Toast.LENGTH_SHORT).show();
                rollno.setText("");
                name.setText("");
                sem.setText("");
            }
        });
    }
}