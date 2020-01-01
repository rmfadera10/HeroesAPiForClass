package BLL;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.widget.Toast;

import com.example.heroesapiforclass.LoginActivity;
import com.example.heroesapiforclass.MainActivity;

import java.io.IOException;

import heroesapi.HeroesAPI;
import model.ImageResponse;
import model.LoginSignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.Url;

public class LoginBLL {
    private String username;
    private String password;
    //Context context;
    boolean isSuccess = false;

    public LoginBLL(String username, String password) {
        //this.context = context;
        this.username = username;
        this.password = password;
    }

    private void StrictMode() {
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

    public boolean checkUser() {
        HeroesAPI heroesAPI = Url.getInstance().create(HeroesAPI.class);
        Call<LoginSignupResponse> usersCall = heroesAPI.checkUser(username, password);

        StrictMode();
        try {
            Response<LoginSignupResponse> imageResponseResponse = usersCall.execute();
            // After saving an image, retrieve the current name of the image
            if (imageResponseResponse.body().getSuccess()) {
                Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}