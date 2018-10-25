package com.thunderx.locationapp.network;

import com.thunderx.locationapp.network.model.UserResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {

    // Fetch users
    @GET("?inc=name,email,dob,phone,picture&results=5")
    Single<UserResponse> fetchUsers();

}
