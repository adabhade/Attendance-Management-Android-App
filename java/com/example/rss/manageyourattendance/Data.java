package com.example.rss.manageyourattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Data extends AppCompatActivity {
    String JSONString;
    JSONObject jsonObject;
    JSONArray jsonArray;
    DataAdapter DataAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        listView = (ListView) findViewById(R.id.listview);
        DataAdapter = new DataAdapter(this,R.layout.row_layout);
        listView.setAdapter(DataAdapter);
        JSONString = getIntent().getExtras().getString("JSONData");
        try
        {
            jsonObject = new JSONObject(JSONString);
            jsonArray = jsonObject.getJSONArray("serverData");
            int count = 0;
            String ID,name;
            while(count < jsonArray.length())
            {
                JSONObject jo = jsonArray.getJSONObject(count);
                ID = jo.getString("ID");
                name = jo.getString("NAME");
                StudentData StudentData = new StudentData(ID,name);
                DataAdapter.add(StudentData);
                count = count + 1;
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
