package com.example.rss.manageyourattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StudentOperations extends AppCompatActivity {
    TextView tv;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_operations);

        tv = (TextView) findViewById(R.id.textView5);
        name = getIntent().getStringExtra("Name");
        tv.setText(name);
    }
}
