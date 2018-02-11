package com.example.rss.manageyourattendance;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TeacherOperations extends AppCompatActivity {
    TextView tv;
    Button btn;
    String division,JSONString;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_operations);

        tv = (TextView) findViewById(R.id.textViewDetails);
        division = getIntent().getStringExtra("Division");
        tv.setText(division);

        btn = (Button) findViewById(R.id.buttonDetaisOfStudent);
        intent = new Intent(this,Data.class);
    }
    public void showDetails(View view)
    {
        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void,Void,String >
    {
        String JSONUrl;

        @Override
        protected String doInBackground(Void... params)
        {
            try
            {
                URL url = new URL(JSONUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("division","UTF-8")+"="+URLEncoder.encode(division,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream is = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String recv = "";
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();
                while((JSONString = br.readLine()) != null)
                    stringBuilder.append(JSONString+"\n");
                br.close();
                is.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            JSONUrl = "http://attendance-systemapp.000webhostapp.com/retrieveJSON.php";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result)
        {
            JSONString = result;
            Toast.makeText(getBaseContext(),"Connected to database",Toast.LENGTH_SHORT).show();
            intent.putExtra("JSONData", JSONString);
            startActivity(intent);
        }
    }
}
