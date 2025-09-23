package com.le.pr7;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends Activity {
    String name[]={"AAA","BBB","CCC","DDD","EEE"};
    int img[]={R.drawable.first,R.drawable.s,R.drawable.t,R.drawable.f,R.drawable.fi};

    ListView l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        l = (ListView) findViewById(R.id.list_view);
        Custom_Adapter c = new Custom_Adapter();

        l.setAdapter(c);

    }
    class Custom_Adapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return img.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
           View v = getLayoutInflater().inflate(R.layout.list_view_design,null);


           TextView t = v.findViewById(R.id.item_text);
            ImageView im = v.findViewById(R.id.item_img);
            t.setText(name[i]);
            im.setImageResource(img[i]);



            return v;
        }
    }



}