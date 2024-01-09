package com.kaushlendraprajapati.demo2app.Interface;

import com.kaushlendraprajapati.demo2app.Modal.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {

    @GET("/users")
    Call<List<Users>> getUsers();
}
