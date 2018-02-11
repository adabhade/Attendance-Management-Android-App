package com.example.rss.manageyourattendance;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class StudentLogin extends AppCompatActivity {
    EditText etUsername,etPassword;
    Button btn;
    String username,password;
    Intent intent;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        etUsername = (EditText) findViewById(R.id.editTextSUsername);
        etPassword = (EditText) findViewById(R.id.editTextSPassword);
        btn = (Button) findViewById(R.id.button2);
        intent = new Intent(getBaseContext(),StudentOperations.class);
    }
    public void StudentLoginFunction(View view)
    {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Logging in");
        progressDialog.setMessage("Please wait ...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        LoginClass loginClass = new LoginClass(this);
        loginClass.execute(username,password);
    }

    public class LoginClass extends AsyncTask<String,Void,String> {
        Context context;
        AlertDialog alertDialog;

        LoginClass(Context context) {
            this.context = context;
        }

        @Override
        protected void onPostExecute(String result) {
            if (!(result.equals("Wrong userID or password")))
            {
                String name = result;
                intent.putExtra("Name",name);
                startActivity(intent);
            }
            else
            {
                progressDialog.cancel();
                alertDialog.setMessage(result);
                alertDialog.show();
            }
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Information :-");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
            String teacherLoginURL = "http://attendance-systemapp.000webhostapp.com/Login_Student.php";
            String username = params[0];
            String password = params[1];
            URL url;
            try {
                url = new URL(teacherLoginURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream is = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String recv = "";
                String line = "";
                while ((line = br.readLine()) != null)
                    recv = recv + line;
                br.close();
                is.close();
                httpURLConnection.disconnect();
                return recv;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
