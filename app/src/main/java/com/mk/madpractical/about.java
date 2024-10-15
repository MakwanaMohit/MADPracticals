package com.mk.madpractical;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class about extends AppCompatActivity {
    TextView author,source;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        author = findViewById(R.id.authorName);
        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://codewithmk.site";

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        source = findViewById(R.id.sourceCode);
        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://github.com/MakwanaMohit/MAD-Practical";

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        Uri data = intent.getData();

        if (data != null) {
            // Extract the username from the path
            String path = data.getPath();
            String username = path.replace("/MAD/", "");
            Toast.makeText(this, "user is:"+username, Toast.LENGTH_SHORT).show();

            // Extract query parameters (e.g., ?roll=36)
            String roll = data.getQueryParameter("roll");
            Toast.makeText(this, "Roll is:"+roll, Toast.LENGTH_SHORT).show();

            // Capture POST request data (if any)
            capturePostRequestData(intent);
        }
    }

    private void capturePostRequestData(Intent intent) {
        // Check if the intent contains POST data (from a web form or another source)
        Bundle extras = intent.getExtras();
        if (extras != null) {
            // Iterate over the POST data
            for (String key : extras.keySet()) {
                Object value = extras.get(key);
                Log.d("PostData", key + ": " + value.toString());
            }
        } else {
            Log.d("PostData", "No POST data found.");
        }
    }
}


