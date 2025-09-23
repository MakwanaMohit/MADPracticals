package com.le.pr3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText no1,no2;
    Button plus,minus,multi,divi,modu;
    TextView result;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plus = (Button) findViewById(R.id.btn_plus);
        minus = (Button) findViewById(R.id.btn_min);
        multi = (Button) findViewById(R.id.btn_mult);
        divi = (Button) findViewById(R.id.btn_div);
        modu = (Button) findViewById(R.id.btn_mod);
        no1 = (EditText) findViewById(R.id.edt_1);
        no2 = (EditText) findViewById(R.id.edt_2);
        result = (TextView) findViewById(R.id.txt_result);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1 = no1.getText().toString();
                String n2 = no2.getText().toString();
                int n_1 = Integer.parseInt(n1);
                int n_2 = Integer.parseInt(n2);

                int sum = n_1+n_2;
                String r = String.valueOf(sum);
                result.setText(r);



            }
        });







    }
}