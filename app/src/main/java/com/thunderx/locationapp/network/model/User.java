package com.thunderx.locationapp.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    private boolean isPhoneUdated = false;

    @SerializedName("name")
    @Expose
    private UserNameItem userNameItem;

    @SerializedName("dob")
    @Expose
    private DobItem dobItem;

    @SerializedName("picture")
    @Expose
    private PictureItem pictureItem;

    public String getEmail() {
        return email;
    }

    public String getPhone() {

        if(!isPhoneUdated)
        {
            try {
                phone = phone.replaceAll("[^a-zA-Z0-9]", "");
                isPhoneUdated = true;
            }
            catch(Exception e)
            {
                e.printStackTrace();

            }
        }

        return phone;
    }

    public UserNameItem getUserNameItem() {
        return userNameItem;
    }

    public DobItem getDobItem() {
        return dobItem;
    }

    public PictureItem getPictureItem() {
        return pictureItem;
    }
}


