package com.example.rss.manageyourattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.buttonStudent);
        b2 = (Button)findViewById(R.id.buttonTeacher);
    }
    public void TeacherLogin(View view)
    {
        Intent intent = new Intent(this,TeacherLogin.class);
        startActivity(intent);
    }
    public void StudentLogin(View view)
    {
        Intent intent = new Intent(this,StudentLogin.class);
        startActivity(intent);
    }
}
