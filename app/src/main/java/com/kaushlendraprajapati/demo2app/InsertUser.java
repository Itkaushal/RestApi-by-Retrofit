package com.kaushlendraprajapati.demo2app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.kaushlendraprajapati.demo2app.Interface.InsertUserApi;
import com.kaushlendraprajapati.demo2app.Modal.InsertUserModal;
import com.kaushlendraprajapati.demo2app.databinding.ActivityInsertUserBinding;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class InsertUser extends AppCompatActivity {

    ActivityInsertUserBinding insertUserBinding;
    String urlget="http://192.168.43.197:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        insertUserBinding=ActivityInsertUserBinding.inflate(getLayoutInflater());
        setContentView(insertUserBinding.getRoot());


       insertUserBinding.button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String name= insertUserBinding.etName.getText().toString();
               String email= insertUserBinding.etEmail.getText().toString();
               String mobile= insertUserBinding.etMobile.getText().toString();
               String password= insertUserBinding.etPassword.getText().toString();
               insertUser(name,email,mobile,password);

           }
       });

       insertUserBinding.gonextbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent obj = new Intent(InsertUser.this,HomeActivity.class);
               startActivity(obj);
           }
       });
    }

    private void insertUser(String name, String email, String mobile, String password) {
        insertUserBinding.progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(urlget)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InsertUserModal insertUserModal = new InsertUserModal(name, email, mobile, password);
        InsertUserApi userApi=retrofit2.create(InsertUserApi.class);
        Call<InsertUserModal> call= userApi.userInsert(insertUserModal);
        call.enqueue(new Callback<InsertUserModal>() {
            @Override
            public void onResponse(Call<InsertUserModal>call, Response<InsertUserModal>response) {
                insertUserBinding.progressBar.setVisibility(View.GONE);
                insertUserBinding.button.setEnabled(false);
                insertUserBinding.button.setBackgroundResource(R.color.fade);
                Toast.makeText(InsertUser.this, "user inserted successfully", Toast.LENGTH_SHORT).show();
                insertUserBinding.etName.setText("");
                insertUserBinding.etEmail.setText("");
                insertUserBinding.etMobile.setText("");
                insertUserBinding.etPassword.setText("");

                InsertUserModal responseFromApi = (InsertUserModal) response.body();
                String name = Objects.requireNonNull(responseFromApi).getName();
                String email = responseFromApi.getEmail();
                String mobile = responseFromApi.getMobile();
                String code = "Response Code : "+response.code();

                insertUserBinding.name.setText(name);
                insertUserBinding.email.setText(email);
                insertUserBinding.mobile.setText(mobile);
                insertUserBinding.password.setText(code);

            }

            @Override
            public void onFailure(Call<InsertUserModal> call, Throwable t) {

                insertUserBinding.user.setText("error is : "+t.getMessage());


            }
        });

    }
}