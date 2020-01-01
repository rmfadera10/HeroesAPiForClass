package com.example.heroesapiforclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.List;
import adapter.HeroesAdapter;
import heroesapi.HeroesAPI;
import model.Heroes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.Url;

public class HeroesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);

        recyclerView = findViewById(R.id.recyclerView);

        getAllHeroes();
    }

    private void getAllHeroes() {
        HeroesAPI heroesAPI = Url.getInstance().create(HeroesAPI.class);

        Call<List<Heroes>> listCall = heroesAPI.getAllHeroes(Url.Cookie);

        listCall.enqueue(new Callback<List<Heroes>>() {
            @Override
            public void onResponse(Call<List<Heroes>> call, Response<List<Heroes>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(HeroesActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Heroes> heroesList = response.body();
                //Pass List to the Adapter class
                HeroesAdapter contactsAdapter = new HeroesAdapter(heroesList, HeroesActivity.this);
                recyclerView.setAdapter(contactsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(HeroesActivity.this));

            }

            @Override
            public void onFailure(Call<List<Heroes>> call, Throwable t) {
                Toast.makeText(HeroesActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
