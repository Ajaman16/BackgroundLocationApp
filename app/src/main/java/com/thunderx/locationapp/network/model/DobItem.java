package com.thunderx.locationapp.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DobItem{

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("age")
    @Expose
    private int age;

    private long timestamp = 0;
    private String dateNew = "";
    private boolean isConverted = false;

    public String getDate() {

        if(!isConverted)
        {

            try {
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Date dt = formatter.parse(date);

                formatter.applyPattern("dd/MM/yyyy");
                dateNew = formatter.format(dt);

                timestamp = dt.getTime();
                isConverted = true;

            } catch (ParseException e) {
                e.printStackTrace();
                dateNew = date;
                timestamp = 0;
            }

        }

        return dateNew;
    }

    public int getAge() {
        return age;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
