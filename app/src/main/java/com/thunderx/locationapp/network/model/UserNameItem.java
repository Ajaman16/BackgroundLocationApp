package com.thunderx.locationapp.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserNameItem {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("first")
    @Expose
    private String first;

    @SerializedName("last")
    @Expose
    private String last;

    private boolean isUpperCase = false;

    public String getTitle() {
        return title;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getName(){

        if(!isUpperCase)
        {
            first = first.substring(0, 1).toUpperCase() + first.substring(1);
            last = last.substring(0, 1).toUpperCase() + last.substring(1);
            isUpperCase = true;
        }

        return first + " " + last;
    }
}
