package com.example.rss.manageyourattendance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RSS on 25-09-2017.
 */

public class DataAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public DataAdapter(Context context, int resource)
    {
        super(context,resource);
    }

    public void add(StudentData object) {
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        Holder holder = null;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            holder = new Holder();
            holder.tvID = (TextView) row.findViewById(R.id.textViewID);
            holder.tvName = (TextView) row.findViewById(R.id.textViewName);
            row.setTag(holder);
        }
        else
        {
            holder = (Holder) row.getTag();
        }
        StudentData studentData = (StudentData) this.getItem(position);
        holder.tvID.setText(studentData.getID());
        holder.tvName.setText(studentData.getName());
        return row;
    }

    static class Holder
    {
        TextView tvID,tvName;
    }
}
