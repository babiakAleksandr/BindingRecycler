package com.example.alexandr.bindingrecycler.net;

import com.example.alexandr.bindingrecycler.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Alexandr on 27.01.2017.
 */

public interface ApiService {

    @GET("/users")
    Call<List<User>> getPhotos();



}