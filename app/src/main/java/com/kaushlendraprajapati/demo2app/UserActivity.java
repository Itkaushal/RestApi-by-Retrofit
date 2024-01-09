package com.kaushlendraprajapati.demo2app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kaushlendraprajapati.demo2app.Interface.UserApi;
import com.kaushlendraprajapati.demo2app.Modal.Users;
import com.kaushlendraprajapati.demo2app.databinding.ActivityUserBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {

    ActivityUserBinding userBinding;
    String url2="https://jsonplaceholder.typicode.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBinding=ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(userBinding.getRoot());

        userBinding.next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(getApplicationContext(),InsertUser.class);
                startActivity(obj);
            }
        });

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(url2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserApi userApi = retrofit2.create(UserApi.class);
        Call<List<Users>> listCall = userApi.getUsers();
        listCall.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                List<Users> data=response.body();

                for (int i=0; i<data.size(); i++)
                {
                    userBinding.textviewDetails.append("Id: "+data.get(i).getId()+"\n Name:"+data.get(i).getName()+"\n UserName: "+data.get(i).getUsername()+"\n Email: "+data.get(i).getEmail()+"\n \n \n");
                }

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

                Toast.makeText(UserActivity.this, "Failed!"+t, Toast.LENGTH_SHORT).show();
                userBinding.textviewDetails.setText(String.format("Error is : %s", t));

            }
        });
    }
}