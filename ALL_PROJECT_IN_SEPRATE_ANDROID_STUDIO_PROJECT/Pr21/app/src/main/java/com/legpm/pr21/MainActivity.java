package com.legpm.pr21;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private ArrayList<Student> students;
    private StudentAdapter adapter;
    private static final String URL_STRING = "http://192.168.66.127:5700/MAD-Pr21.php"; // Replace with your PHP URL
    private final OkHttpClient client = new OkHttpClient();
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
        recyclerView = findViewById(R.id.Recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        students = new ArrayList<Student>();
        adapter = new StudentAdapter(this, students);
        recyclerView.setAdapter(adapter);
        fetchStudentData();
    }
    private void fetchStudentData() {
        // Build the request to fetch data from the server
        Request request = new Request.Builder()
                .url(URL_STRING)
                .build();

        // Make an asynchronous call using OkHttp
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "Error fetching the data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();
                    runOnUiThread(() -> {
                        try {
                            parseJsonToStudentList(responseData);
                        } catch (JSONException e) {
                            Log.e("MainActivity", "Error parsing JSON", e);
                        }
                    });
                }
            }
        });
    }

    // Method to parse the JSON response and return a list of Student objects
    private List<Student> parseJsonToStudentList(String jsonData) throws JSONException {
        List<Student> studentList = new ArrayList<>();

        // Parse the JSON array from the response
        JSONArray jsonArray = new JSONArray(jsonData);

        // Loop through each object in the JSON array
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject studentJson = jsonArray.getJSONObject(i);

            // Extract the values and create a Student object
            String rollno = studentJson.getString("rollno");
            String name = studentJson.getString("name");
            String sem = studentJson.getString("sem");

            // Create the Student object, keeping key same as rollno
            Student student = new Student(rollno, rollno, name, sem);

            // Add the student object to the list
            adapter.addStudent(student);
            studentList.add(student);
        }

        return studentList;
    }

}

