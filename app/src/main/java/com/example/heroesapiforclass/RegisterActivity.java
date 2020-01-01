package com.example.heroesapiforclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import heroesapi.HeroesAPI;
import model.LoginSignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;
import url.Url;

public class RegisterActivity extends AppCompatActivity {
    private EditText etRUsername, etRPassword, etRConfirmPass;
    private Button btnRRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etRUsername = findViewById(R.id.etRUsername);
        etRPassword = findViewById(R.id.etRPassword);
        etRConfirmPass = findViewById(R.id.etRConfirmPass);

        btnRRegister = findViewById(R.id.btnRRegister);

        btnRRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });
    }

    private void RegisterUser() {
        if (!etRPassword.getText().toString().equals(etRConfirmPass.getText().toString())) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
            etRConfirmPass.setText("");
            return ;
        }


        String username = etRUsername.getText().toString();
        String password = etRPassword.getText().toString();

        HeroesAPI heroesAPI = Url.getInstance().create(HeroesAPI.class);

        Call<LoginSignupResponse> call = heroesAPI.registerUser(username,password);

        call.enqueue(new Callback<LoginSignupResponse>() {
            @Override
            public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "Server response " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Successsfully registered" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginSignupResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
