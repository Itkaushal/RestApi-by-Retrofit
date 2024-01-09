package com.kaushlendraprajapati.demo2app.Interface;

import com.kaushlendraprajapati.demo2app.Modal.Modal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi
{
    @GET("/posts")
    Call<List<Modal>> getModals();
}
