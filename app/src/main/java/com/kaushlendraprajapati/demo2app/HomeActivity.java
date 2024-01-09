package com.kaushlendraprajapati.demo2app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kaushlendraprajapati.demo2app.Adapter.HomeAdapter;
import com.kaushlendraprajapati.demo2app.Interface.HomeApi;
import com.kaushlendraprajapati.demo2app.Modal.HomeModalClass;
import com.kaushlendraprajapati.demo2app.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding homeBinding;
    RecyclerView recyclerview;
    HomeAdapter adapter;
    List<HomeModalClass> homeModalClassList = new ArrayList<>();
    LinearLayoutManager layoutManager;

    String urlhome="http://192.168.43.197:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        recyclerview=homeBinding.recyclerView;
        layoutManager= new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        adapter=new HomeAdapter(homeModalClassList);
        recyclerview.setAdapter(adapter);

        fetchUserData();
    }

    private void fetchUserData() {
        homeBinding.progressBar2.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlhome)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HomeApi homeApi = retrofit.create(HomeApi.class);
        Call<List<HomeModalClass>> call = homeApi.gethomemodallist();
        call.enqueue(new Callback<List<HomeModalClass>>() {
            @Override
            public void onResponse(Call<List<HomeModalClass>> call, Response<List<HomeModalClass>> response) {
                List<HomeModalClass> data = response.body();
                homeBinding.progressBar2.setVisibility(View.GONE);
               if (data!=null)
               {
                   homeModalClassList.addAll(data);
                   adapter.notifyDataSetChanged();
               }

            }

            @Override
            public void onFailure(Call<List<HomeModalClass>> call, Throwable t) {

                Toast.makeText(HomeActivity.this, "Error is : "+t, Toast.LENGTH_SHORT).show();

            }
        });
    }
}