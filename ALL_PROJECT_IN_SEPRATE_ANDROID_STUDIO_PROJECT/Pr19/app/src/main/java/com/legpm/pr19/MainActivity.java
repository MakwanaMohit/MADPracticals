package com.legpm.pr19;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ExecutorService executorService;
    private Handler mainHandler;
    TextView rollno, name, sem;
    Button action;

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

        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
        rollno = findViewById(R.id.main_RollNo);
        name = findViewById(R.id.main_Name);
        sem = findViewById(R.id.main_Semester);
        action = findViewById(R.id.main_action_btn);
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
                insertData(rollnoval, nameval, semval);
            }
        });
    }


    private void insertData(final String rollnoval, final String nameval, final String semval) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // Make the HTTP request in the background thread
                    URL url = new URL("http://192.168.162.127:5700/MAD-Pr19.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    // Prepare POST data
                    String postData = "rollno=" + rollnoval + "&name=" + nameval + "&sem=" + semval;

                    // Send POST data
                    OutputStream os = conn.getOutputStream();
                    os.write(postData.getBytes());
                    os.flush();
                    os.close();

                    // Get the response from the server
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // Send the server response to the main thread
                    final String result = response.toString();
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            // Show the server response as a Toast on the main thread
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
//                            rollno.setText("");
//                            name.setText("");
//                            sem.setText("");
                        }
                    });

                } catch (Exception e) {
                    Log.e("Error", e.getMessage());

                    // Post the error message to the main thread
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Error occurred:" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}