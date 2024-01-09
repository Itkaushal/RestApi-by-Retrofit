package com.kaushlendraprajapati.demo2app.Interface;

import com.kaushlendraprajapati.demo2app.Modal.InsertUserModal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InsertUserApi {

    @POST("/insertData")
    Call<InsertUserModal> userInsert(@Body InsertUserModal insertUserModal);
}
