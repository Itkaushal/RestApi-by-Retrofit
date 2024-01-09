package com.kaushlendraprajapati.demo2app;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.kaushlendraprajapati.demo2app.Interface.MyApi;
import com.kaushlendraprajapati.demo2app.Modal.Modal;
import com.kaushlendraprajapati.demo2app.databinding.ActivityMainBinding;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {

    String url="https://jsonplaceholder.typicode.com";
    ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi api = retrofit.create(MyApi.class);

        Call<List<Modal>> call = api.getModals();
        call.enqueue(new Callback<List<Modal>>() {
            @Override
            public void onResponse(Call<List<Modal>> call, Response<List<Modal>> response) {
                List<Modal> data = response.body();
                for (int i=0; i<data.size(); i++)
                {
                    mainBinding.textView.append("Sl No:"+data.get(i).getId()+"\n title:"+data.get(i).getTitle()+"\n \n \n");
                }
            }

            @Override
            public void onFailure(Call<List<Modal>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "LoadingFailed!"+t, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void nextpage(View view) {
        Intent intent = new Intent(getApplicationContext(),UserActivity.class);
        startActivity(intent);
    }
}
