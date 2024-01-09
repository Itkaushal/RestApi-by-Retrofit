package com.kaushlendraprajapati.demo2app.Interface;

import com.kaushlendraprajapati.demo2app.Modal.HomeModalClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeApi {

    @GET("/getAllData")
    Call<List<HomeModalClass>> gethomemodallist();
}
