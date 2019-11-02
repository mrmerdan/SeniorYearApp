package com.example.senioryearapp;

import java.io.Serializable;

public class locationEntry implements Serializable {
    public String id;
    public String name;
    public String time;
    public String frequency;



    public locationEntry(){
        this.name = "";
        this.time = "";
        this.frequency = "";
    }

    public locationEntry(String name, String time, String frequency){
        this.name = name;
        this.time = time;
        this.frequency = frequency;
    }


    @Override
    public String toString(){
        return "name: " + name + " time: " + time + " frequency:" + frequency;
    }

}
