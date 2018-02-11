package com.example.rss.manageyourattendance;

/**
 * Created by RSS on 25-09-2017.
 */

public class StudentData
{
    String name;
    String ID;

    public StudentData(String ID,String name)
    {
        this.setID(ID);
        this.setName(name);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
