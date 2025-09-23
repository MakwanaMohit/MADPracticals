package com.mad.pr13;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView textView;
    private static final Uri CONTENT_URI = Uri.parse("content://com.mad.pr13/hello");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Practical13_ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        try {
            btn = findViewById(R.id.Practical13_button);
            textView = findViewById(R.id.Practical13_textview);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContentResolver contentResolver = getContentResolver();
                    Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        String msg = cursor.getString(cursor.getColumnIndex("message"));
                        textView.setText(msg);
                        Toast.makeText(MainActivity.this, "receives: "+msg, Toast.LENGTH_SHORT).show();
                        cursor.close();
                    }
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
